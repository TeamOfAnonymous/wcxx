package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation.CategoryTree;
import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.domain.User;
import com.anonymous.repository.PropagandaInformationCategoryRepository;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by WangZK on 2017/6/1.
 */
@Service
public class PropagandaInformationCategoryService implements PropagandaInformationCategoryServiceInter {

    @Autowired
    private PropagandaInformationCategoryRepository propagandaInformationCategoryRepository;
    @Autowired
    private PropagandaInformationServiceInter propagandaInformationService;

    @Override
    public PropagandaInformationCategory add(PropagandaInformationCategory propagandaInformationCategory) {
        propagandaInformationCategory.setLastModifiedTime(LocalDateTime.now());
        User user = new User();
        user.setId("qweqwe");
        propagandaInformationCategory.setCreator(user);
        return propagandaInformationCategoryRepository.save(propagandaInformationCategory);
    }

    @Override
    public PropagandaInformationCategory edit(PropagandaInformationCategory propagandaInformationCategory) {
        propagandaInformationCategory.setLastModifiedTime(LocalDateTime.now());
        return propagandaInformationCategoryRepository.save(propagandaInformationCategory);
    }

    @Override
    public boolean delete(String id) {
        //判断是否存在子节点，如果存在，不给删除，如果不存在，则可以删除
        List<PropagandaInformationCategory> propagandaInformationCategories = propagandaInformationCategoryRepository.findByPid(id);
        if (propagandaInformationCategories.size() > 0) {
            return false;
        } else {
            List<PropagandaInformation> propagandaInformations = propagandaInformationService.findByPropagandaInformationCategories(id);
            if (propagandaInformations.size() > 0) {
                return false;
            }
        }
        propagandaInformationCategoryRepository.delete(id);
        return true;
    }

    @Override
    public PropagandaInformationCategory findById(String id) {
        return propagandaInformationCategoryRepository.findOne(id);
    }

    @Override
    public List<PropagandaInformationCategory> findByPidIsNull() {
        return propagandaInformationCategoryRepository.findByPidIsNull();
    }

    @Override
    public List<PropagandaInformationCategory> findByPid(String pid) {
        return propagandaInformationCategoryRepository.findByPid(pid);
    }

    /**
     * 获取树形结构的全部字段
     *
     * @param mainCategory
     * @param medium
     * @return
     */
    @Override
    public Object[] getCategoriesOfTree(String mainCategory, String medium) {

        System.out.println(mainCategory + medium +"----------------------------------------");

        if ( !StringUtils.isEmpty(mainCategory) || !StringUtils.isEmpty(medium) ) {

            if (!StringUtils.isEmpty(mainCategory) && !StringUtils.isEmpty(medium)) { //两者都不为空

                System.out.println("测试：两者都不为空！");

                List<PropagandaInformationCategory> propagandaInformationCategoryList = new ArrayList<>();

                for (PropagandaInformationCategory propagandaInformationCategory : this.findByFatherIds(findByExample(mainCategory))) {
                    if( !StringUtils.isEmpty(propagandaInformationCategory.getPid()) && StringUtils.substringMatch(propagandaInformationCategory.getName(), 0, medium))
                        propagandaInformationCategoryList.add(propagandaInformationCategory);
                }

                return castToCategoryTreeList(propagandaInformationCategoryList);

            } else if (!StringUtils.isEmpty(medium)) { // medium不为空

                System.out.println("测试：medium不为空！");
                return castToCategoryTreeList(this.propagandaInformationCategoryRepository.findByNameAndPidNotNull(medium));

            } else { // mainCategory不为空

                System.out.println("测试：mainCategory不为空！");
                return this.castToDisplayTree(this.findByFatherIds(findByExample(mainCategory)));

            }
        } else { // 两者都为空 则为查找全部
            System.out.println("测试：两者都为空！");
            return castToDisplayTree(this.propagandaInformationCategoryRepository.findAll(new Sort(Sort.Direction.DESC, "sortNum")));
        }
    }

    private List<PropagandaInformationCategory> findByFatherIds(List<PropagandaInformationCategory> propagandaInformationCategoryList){
        List<PropagandaInformationCategory> filterList = new ArrayList<>();
        for ( PropagandaInformationCategory propagandaInformationCategory: propagandaInformationCategoryList ) {
            if (!StringUtils.isEmpty(propagandaInformationCategory.getPid()))
                continue;
            filterList.add(propagandaInformationCategory);
            filterList.addAll(propagandaInformationCategoryRepository.findByPid(propagandaInformationCategory.getId()));
        }
        return filterList;
    }

    private List<PropagandaInformationCategory> findByExample(String name) {

        PropagandaInformationCategory propagandaInformationCategory = new PropagandaInformationCategory();

        propagandaInformationCategory.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains)
                .withIgnorePaths("id", "status", "sortNum", "lastModifiedTime", "pid", "creator")
                .withIgnoreNullValues();

        Example<PropagandaInformationCategory> example = Example.of(propagandaInformationCategory, matcher);

        return this.propagandaInformationCategoryRepository.findAll(example, new Sort(Sort.Direction.DESC, "sortNum"));

    }

    /**
     * 转成分类管理的主列表
     */
    private Object[] castToCategoryTreeList(List<PropagandaInformationCategory> propagandaInformationCategories){
        List<CategoryTree> categoryTreeList = new ArrayList<>();
        CategoryTree categoryTree;
        for (PropagandaInformationCategory p : propagandaInformationCategories){
            categoryTree = new CategoryTree();
            categoryTree.setId(p.getId());
            categoryTree.setText(p.getName());
            categoryTree.setPid(p.getPid());
            categoryTree.setCreator(p.getCreator().getName());
            categoryTree.setStatus(p.getStatus());
            categoryTree.setLastModifiedTime(p.getLastModifiedTime());
            categoryTreeList.add(categoryTree);
        }
        return categoryTreeList.toArray();
    }

    /**
     * 转成分类管理的下拉框的树
     */
    private Object[] castToDisplayTree(List<PropagandaInformationCategory> propagandaInformationCategories){
        List<CategoryTree> temp = new ArrayList<>();
        CategoryTree sonNode;
        CategoryTree grandSonNode;

        for (PropagandaInformationCategory p : propagandaInformationCategories){
            if (!StringUtils.isEmpty(p.getPid()))
                continue;
            sonNode = new CategoryTree();
            sonNode.setId(p.getId());
            sonNode.setPid("");
            sonNode.setCreator(p.getCreator().getName());
            sonNode.setStatus(p.getStatus());
            sonNode.setLastModifiedTime(p.getLastModifiedTime());
            sonNode.setText(p.getName());
            for (PropagandaInformationCategory sp : propagandaInformationCategories) {
                if ( sonNode.getId().equals(sp.getPid()) ){
                    if(null == sonNode.getNodes())
                        sonNode.setNodes(new ArrayList<>());
                    grandSonNode = new CategoryTree();
                    grandSonNode.setId(sp.getId());
                    grandSonNode.setText(sp.getName());
                    grandSonNode.setPid(sp.getPid());
                    grandSonNode.setCreator(sp.getCreator().getName());
                    grandSonNode.setStatus(sp.getStatus());
                    grandSonNode.setLastModifiedTime(sp.getLastModifiedTime());
                    sonNode.getNodes().add(grandSonNode);
                }
            }
            temp.add(sonNode);
        }
        Object[] objects = temp.toArray();
        return objects;
    }
 }
