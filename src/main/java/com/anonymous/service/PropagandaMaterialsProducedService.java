package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsContent;
import com.anonymous.domain.PropagandaMaterialsProduced;
import com.anonymous.repository.PropagandaMaterialsProducedRepository;
import com.anonymous.service.inter.PropagandaMaterialsProducedServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品制作申请 Service层实现
 */
@Service
public class PropagandaMaterialsProducedService implements PropagandaMaterialsProducedServiceInter {

    /**
     * 宣传品内容 service
     */
    private  PropagandaMaterialsContentService propagandaMaterialsContentService;

    /**
     * 宣传品制作申请 dao
     */
    private PropagandaMaterialsProducedRepository propagandaMaterialsProducedRepository;

    public PropagandaMaterialsProducedService(@Autowired PropagandaMaterialsProducedRepository propagandaMaterialsProducedRepository ,@Autowired PropagandaMaterialsContentService propagandaMaterialsContentService){
        this.propagandaMaterialsContentService = propagandaMaterialsContentService;
        this.propagandaMaterialsProducedRepository = propagandaMaterialsProducedRepository;
    }

    @Override
    public PropagandaMaterialsProduced findById(String id) {

        return propagandaMaterialsProducedRepository.findOne(id);
    }

    /**
     * 添加 宣传品制作申请 到草稿箱
     * @param propagandaMaterialsProduced
     * @return
     */
    @Override
    public PropagandaMaterialsProduced add(PropagandaMaterialsProduced propagandaMaterialsProduced ,String propagandaInformationId) {
        // 初始化总费用
        float totalCost = 0;

        // 先把该申请里面的 宣传品内容 保存了
        List<PropagandaMaterialsContent> propagandaMaterialsContents = propagandaMaterialsProduced.getPropagandaMaterialsContents();
        for (PropagandaMaterialsContent propagandaMaterialsContent : propagandaMaterialsContents ) {
            propagandaMaterialsContentService.add(propagandaMaterialsContent);
            // 更新 总费用
            totalCost += propagandaMaterialsContent.getCost();
        }

        //TODO 给该申请添加 申请人
        //TODO 给该申请添加 负责人
        // 给该申请添加 总费用
        propagandaMaterialsProduced.setTotalCost(totalCost);
        // 给该申请设置 审批状态为0 : 0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档
        propagandaMaterialsProduced.setApprovalStatus(0);
        //TODO 给该申请添加 所属宣传信息申请

        return propagandaMaterialsProducedRepository.save(propagandaMaterialsProduced);
    }

    /**
     * 提交申请
     * TODO 方法参数 id 还是 PropagandaMaterialsProduced 待思考
     * @param id
     * @return
     */
    @Override
    public PropagandaMaterialsProduced apply(String id) {
        PropagandaMaterialsProduced propagandaMaterialsProduced = propagandaMaterialsProducedRepository.findOne(id);
        // 修改 审批状态为1 : 0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档
        propagandaMaterialsProduced.setApprovalStatus(1);
        // 给该申请添加 申请时间
        propagandaMaterialsProduced.setApplicationDate( LocalDateTime.now() );
        return propagandaMaterialsProducedRepository.save(propagandaMaterialsProduced);
    }
}
