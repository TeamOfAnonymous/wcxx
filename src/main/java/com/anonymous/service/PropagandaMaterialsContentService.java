package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsContent;
import com.anonymous.repository.PropagandaMaterialsContentRepository;
import com.anonymous.service.inter.PropagandaMaterialsContentServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品内容 Service层实现
 */
@Service
public class PropagandaMaterialsContentService implements PropagandaMaterialsContentServiceInter {

    @Autowired
    PropagandaMaterialsContentRepository propagandaMaterialsContentRepository;


    @Override
    public PropagandaMaterialsContent findById(String id) {
        return propagandaMaterialsContentRepository.findOne(id);
    }

    /**
     * 添加 宣传品内容
     * @param propagandaMaterialsContent
     * @return
     */
    @Override
    public PropagandaMaterialsContent add(PropagandaMaterialsContent propagandaMaterialsContent) {
        return propagandaMaterialsContentRepository.save(propagandaMaterialsContent);
    }
}