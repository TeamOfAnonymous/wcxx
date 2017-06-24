package com.anonymous.domain.PropagandaWorkProgram;

import com.anonymous.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangMP on 2017/6/18.
 * decription : 宣传工作方案
 */
@Entity
public class PropagandaWorkProgram implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id ;// id

    private LocalDateTime draftDate ;// 拟稿时间

    private String title ; // 标题

    @OneToOne
    private PWPCategory programCategory ; // 方案类别

    @ManyToOne
    private User principal ;// 负责人

    @ManyToMany
    private List<User> members ; // 成员

    private float budget ; // 预算（元）
    private float actualCost ; // 实际费用（元）

    private LocalDate startExecuteTime ; // 开始执行时间
    private LocalDate endExecuteTime ; // 结束执行时间

    @ManyToOne
    private User draftMan ; // 拟稿人

    private String content ; // 内容
    private String remarks ; // 备注

    @Enumerated(EnumType.STRING)
    private PWPApprovalStatus approvalStatus ; // 审批状态，0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档

    @OneToMany(mappedBy = "pwp")
    private List<PWPProject> pwpprojects = new ArrayList<>();//项目列表

    public PropagandaWorkProgram() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(LocalDateTime draftDate) {
        this.draftDate = draftDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PWPCategory getProgramCategory() {
        return programCategory;
    }

    public void setProgramCategory(PWPCategory programCategory) {
        this.programCategory = programCategory;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public float getActualCost() {
        return actualCost;
    }

    public void setActualCost(float actualCost) {
        this.actualCost = actualCost;
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

    public PWPApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(PWPApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public List<PWPProject> getPwpprojects() {
        return pwpprojects;
    }

    public void setPwpprojects(List<PWPProject> pwpprojects) {
        this.pwpprojects = pwpprojects;
    }
}
