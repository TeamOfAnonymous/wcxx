package com.anonymous.service;

import com.anonymous.domain.Organization;
import com.anonymous.domain.PropagandaInformation;
import com.anonymous.domain.PropagandaInformationCategory;
import com.anonymous.repository.PropagandaInformationCategoryRepository;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PropagandaInformationCategory> find() {
        return propagandaInformationCategoryRepository.findByPidIsNull();
    }

    @Override
    public List<PropagandaInformationCategory> findByPid(String pid) {
        return propagandaInformationCategoryRepository.findByPid(pid);
    }
}
