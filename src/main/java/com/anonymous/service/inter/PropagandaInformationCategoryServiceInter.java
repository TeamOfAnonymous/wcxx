package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaInformation.CategoryTree;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;

import java.util.List;
import java.util.Set;

/**
 * Created by WangZK on 2017/6/1.
 */
public interface PropagandaInformationCategoryServiceInter {
    PropagandaInformationCategory add(PropagandaInformationCategory propagandaInformationCategory);

    PropagandaInformationCategory edit(PropagandaInformationCategory propagandaInformationCategory);

    boolean delete(String id);

    PropagandaInformationCategory findById(String propagandaInformationCategory_id);

    List<PropagandaInformationCategory> findByPidIsNull();

    List<PropagandaInformationCategory> findByPid(String pid);

    /**
     * 获取树形结构的全部字段
     * @return
     */
    Object[] getCategoriesOfTree(String mainCategory, String medium, String type);
}
