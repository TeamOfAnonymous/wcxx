package com.anonymous.service.inter;

import com.anonymous.domain.Organization;
import com.anonymous.domain.PropagandaInformationCategory;

import java.util.List;

/**
 * Created by WangZK on 2017/6/1.
 */
public interface PropagandaInformationCategoryServiceInter {
    PropagandaInformationCategory add(PropagandaInformationCategory propagandaInformationCategory);

    PropagandaInformationCategory edit(PropagandaInformationCategory propagandaInformationCategory);

    boolean delete(String id);

    PropagandaInformationCategory findById(String propagandaInformationCategory_id);

    List<PropagandaInformationCategory> find();

    List<PropagandaInformationCategory> findByPid(String pid);
}
