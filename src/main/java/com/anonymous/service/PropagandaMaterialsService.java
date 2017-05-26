package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterials;
import com.anonymous.repository.PropagandaMaterialsRepository;
import com.anonymous.service.inter.PropagandaMaterialsServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZK on 2017/5/26.
 */
@Service
public class PropagandaMaterialsService implements PropagandaMaterialsServiceInter {

    @Autowired
    private PropagandaMaterialsRepository propagandaMaterialsRepository;


    @Override
    public PropagandaMaterials save(PropagandaMaterials propagandaMaterials) {
        return propagandaMaterialsRepository.save(propagandaMaterials);
    }
}
