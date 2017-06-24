package com.anonymous.domain.PropagandaWorkProgram;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by huangMP on 2017/6/18.
 * decription : 宣传工作方案状态
 */
public enum  PWPApprovalStatus {

    Draft("草稿"),
    WaitForApproval("待审批"),
    Approving("审核中"),
    Executing("执行中"),
    Executed("已完成"),
    Pigeonhole("已归档");

    private String name = "";

    PWPApprovalStatus(String name){
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
