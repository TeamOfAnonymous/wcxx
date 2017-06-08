package com.anonymous.domain.PropagandaMaterialsProduced;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by huangMP on 2017/6/5.
 * decription : 审批状态，0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档
 */
public enum ApprovalStatus {

    Draft("草稿"),
    WaitForApproval("待审批"),
    Approving("审核中"),
    Executing("执行中"),
    Executed("已完成"),
    Pigeonhole("已归档");

    private String name = "";

    ApprovalStatus(String name){
        this.name = name ;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name ;
    }
}
