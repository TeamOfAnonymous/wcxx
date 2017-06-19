package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation.CategoryTree;
import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.repository.PropagandaInformationCategoryRepository;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        //根据组织机构名称和父节点进行查询
        PropagandaInformationCategory propagandaInformationCategory1;
        propagandaInformationCategory1 = propagandaInformationCategoryRepository.findByNameAndPid(propagandaInformationCategory.getName(), propagandaInformationCategory.getPid());
        //如果直接返回，不存在再进行保存
        if (propagandaInformationCategory1 != null) {
            return propagandaInformationCategory1;
        }
        return propagandaInformationCategoryRepository.save(propagandaInformationCategory);
    }

    @Override
    public PropagandaInformationCategory edit(PropagandaInformationCategory propagandaInformationCategory) {
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
    public Object[] getCategoriesOfTree(String mainCategory, String medium, String type) {

        Sort sort = new Sort(Sort.Direction.DESC, "sortNum");

        PropagandaInformationCategory propagandaInformationCategory;

        if ( !StringUtils.isEmpty(mainCategory) || !StringUtils.isEmpty(medium) ) {

            propagandaInformationCategory = new PropagandaInformationCategory();

            if (!"".equals(medium))
                propagandaInformationCategory.setName(medium);
            else
                propagandaInformationCategory.setName(mainCategory);

            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withIgnorePaths("id", "status", "sortNum", "lastModifiedTime", "pid", "creator")
                    .withIgnoreNullValues();

            Example<PropagandaInformationCategory> example = Example.of(propagandaInformationCategory, matcher);

            return castToListTree(this.propagandaInformationCategoryRepository.findAll(example, sort));
        } else {
            if ("selectTree".equals(type))
                return castToSelectTree(this.propagandaInformationCategoryRepository.findAll(sort));
            else
                return castToListTree(this.propagandaInformationCategoryRepository.findAll(sort));
        }
    }

    /**
     * 转成分类管理的主列表
     */
    private Object[] castToListTree(List<PropagandaInformationCategory> propagandaInformationCategories){
        Set<PropagandaInformationCategory> set;
        List<Set<PropagandaInformationCategory>> mainList = new ArrayList<>();
        for (PropagandaInformationCategory p : propagandaInformationCategories){
            if (!StringUtils.isEmpty(p.getPid()))
                continue;
            set = new LinkedHashSet<>();
            set.add(p);
            for (PropagandaInformationCategory sp : propagandaInformationCategories) {
                if ( p.getId().equals(sp.getPid()) ){
                    set.add(sp);
                }
            }
            mainList.add(set);
        }
        return mainList.toArray();
    }

    /**
     * 转成分类管理的下拉框的树
     */
    private Object[] castToSelectTree(List<PropagandaInformationCategory> propagandaInformationCategories){
        List<CategoryTree> temp = new ArrayList<>();
        CategoryTree sonNode;
        CategoryTree grandSonNode;

        for (PropagandaInformationCategory p : propagandaInformationCategories){
            if (!StringUtils.isEmpty(p.getPid()))
                continue;
            sonNode = new CategoryTree();
            sonNode.setId(p.getId());
            sonNode.setText(p.getName());
            for (PropagandaInformationCategory sp : propagandaInformationCategories) {
                if ( sonNode.getId().equals(sp.getPid()) ){
                    if(null == sonNode.getNodes())
                        sonNode.setNodes(new ArrayList<>());
                    grandSonNode = new CategoryTree();
                    grandSonNode.setId(sp.getId());
                    grandSonNode.setText(sp.getName());
                    sonNode.getNodes().add(grandSonNode);
                }
            }
            temp.add(sonNode);
        }
        Object[] objects = temp.toArray();
        return objects;
    }
 }
