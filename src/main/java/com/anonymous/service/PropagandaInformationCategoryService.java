package com.anonymous.service;

import com.anonymous.repository.PropagandaInformationCategoryRepository;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZK on 2017/6/1.
 */
@Service
public class PropagandaInformationCategoryService implements PropagandaInformationCategoryServiceInter {

    @Autowired
    private PropagandaInformationCategoryRepository propagandaInformationCategoryRepository;
}
