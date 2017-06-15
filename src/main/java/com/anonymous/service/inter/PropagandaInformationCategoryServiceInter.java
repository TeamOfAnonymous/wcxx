package com.anonymous.service.inter;

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
     * 通过模糊字段查询
     * @param indistinct 模糊字段
     * @return
     */
    Set<List<PropagandaInformationCategory>> findByIndistinctName(String indistinct);

    /**
     * 将数据显示出来
     */
    Set<List<PropagandaInformationCategory>> displayAll();

    /**
     * 将各分类数据封装成map
     */
    Set<List<PropagandaInformationCategory>> packageListToMap(List<PropagandaInformationCategory> propagandaInformationCategories);

}
