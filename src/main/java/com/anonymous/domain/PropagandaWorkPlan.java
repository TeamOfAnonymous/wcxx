package com.anonymous.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/6/3.
 * 宣传工作计划
 */
@Entity
public class PropagandaWorkPlan implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传工作计划id

    private LocalDate draftDate;//拟稿时间

    private String title;//标题
    private String planCategory;//计划类别

    private String groupName;//小组名称

    private String principal;//负责人

    private String planTransactionCategory;//计划事务类别

    private LocalDate startExecuteTime;//开始执行时间
    private LocalDate endExecuteTime;//结束执行时间

    private String members;//成员

    @ManyToOne
    private User draftMan;//拟稿人

    @Column(length = 16777216)
    private String content;//内容

    private String remarks;//备注

    private Integer approvalStatus;//审批状态，0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档

    @OneToMany(mappedBy = "propagandaWorkPlan")
    private List<PlanProject> planProjects = new ArrayList<>();//项目列表

    public PropagandaWorkPlan() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(LocalDate draftDate) {
        this.draftDate = draftDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlanCategory() {
        return planCategory;
    }

    public void setPlanCategory(String planCategory) {
        this.planCategory = planCategory;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPlanTransactionCategory() {
        return planTransactionCategory;
    }

    public void setPlanTransactionCategory(String planTransactionCategory) {
        this.planTransactionCategory = planTransactionCategory;
    }

    public LocalDate getStartExecuteTime() {
        return startExecuteTime;
    }

    public void setStartExecuteTime(LocalDate startExecuteTime) {
        this.startExecuteTime = startExecuteTime;
    }

    public LocalDate getEndExecuteTime() {
        return endExecuteTime;
    }

    public void setEndExecuteTime(LocalDate endExecuteTime) {
        this.endExecuteTime = endExecuteTime;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public User getDraftMan() {
        return draftMan;
    }

    public void setDraftMan(User draftMan) {
        this.draftMan = draftMan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public List<PlanProject> getPlanProjects() {
        return planProjects;
    }

    public void setPlanProjects(List<PlanProject> planProjects) {
        this.planProjects = planProjects;
    }
}
