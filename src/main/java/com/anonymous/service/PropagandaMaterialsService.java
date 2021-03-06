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
    public PropagandaMaterials add(PropagandaMaterials propagandaMaterials) {
        return propagandaMaterialsRepository.save(propagandaMaterials);
    }

    @Override
    public boolean delete(PropagandaMaterials propagandaMaterials) {
        propagandaMaterialsRepository.delete(propagandaMaterials);
        return propagandaMaterialsRepository.findOne(propagandaMaterials.getId()) == null ? true : false;
    }
}
