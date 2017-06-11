package com.anonymous.domain.PropagandaMaterialsProduced.dto;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;

/**
 * Created by huangMP on 2017/6/11.
 * decription : PropagandaMaterialsProducedDto
 */
public class PropagandaMaterialsProducedDto {

    /**
     * 宣传品内容制作方式
     * 提取 PropagandaMaterialsProduced 中的 PropagandaMaterialsContent 的 productionMethod
     */
    private String pmcProductionMethod ;

    /**
     * 组合 宣传品 PropagandaMaterialsProduced
     */
    private PropagandaMaterialsProduced pmp ;

    public String getPmcProductionMethod() {
        return pmcProductionMethod;
    }

    public void setPmcProductionMethod(String pmcProductionMethod) {
        this.pmcProductionMethod = pmcProductionMethod;
    }

    public PropagandaMaterialsProduced getPmp() {
        return pmp;
    }

    public void setPmp(PropagandaMaterialsProduced pmp) {
        this.pmp = pmp;
    }
}
