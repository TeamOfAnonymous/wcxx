package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsContent;
import com.anonymous.repository.PropagandaMaterialsProduced.PropagandaMaterialsContentRepository;
import com.anonymous.service.inter.PropagandaMaterialsContentServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品内容 Service层实现
 */
@Service
public class PropagandaMaterialsContentService implements PropagandaMaterialsContentServiceInter {

    /**
     * 宣传品制作内容 dao
     */
    PropagandaMaterialsContentRepository propagandaMaterialsContentRepository;

    public PropagandaMaterialsContentService(@Autowired PropagandaMaterialsContentRepository propagandaMaterialsContentRepository){
        this.propagandaMaterialsContentRepository = propagandaMaterialsContentRepository ;
    }


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
    public PropagandaMaterialsContent add(PropagandaMaterialsContent propagandaMaterialsContent ) {
        return propagandaMaterialsContentRepository.save(propagandaMaterialsContent);


//        // 通过 宣传品制作申请id 为 宣传品内容 设置其所属的 宣传品制作
//        String PropagandaMaterialsProductId = propagandaMaterialsContent.getPropagandaMaterialsProduced().getId();
//        PropagandaMaterialsProduced propagandaMaterialsProduced = propagandaMaterialsProducedService.findById(PropagandaMaterialsProductId);
//        propagandaMaterialsContent.setPropagandaMaterialsProduced(propagandaMaterialsProduced);
//
//        // 修改 宣传品制作申请的 总费用
//        propagandaMaterialsProduced.setTotalCost( propagandaMaterialsProduced.getTotalCost() + propagandaMaterialsContent.getCost() );
//        propagandaMaterialsProducedService.update(propagandaMaterialsProduced);
//
//        return propagandaMaterialsContentRepository.save(propagandaMaterialsContent);
    }
}