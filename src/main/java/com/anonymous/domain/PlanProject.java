package com.anonymous.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/6/3.
 * 宣传工作计划项目
 */
@Entity
public class PlanProject implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传工作计划项目id

    private String name;//项目名称
    private String content;//项目内容

    private String principal;//负责人

    private Integer completionStatus;//完成状态，0为未完成，1为已完成

    @OneToMany(mappedBy = "planProject")
    private List<PlanProjectCompletion> planProjectCompletions = new ArrayList<>();//工作计划项目的进度详情

    @ManyToOne
    private PropagandaWorkPlan propagandaWorkPlan;//所属工作计划

    private LocalDate deadline;

    public PlanProject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Integer getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(Integer completionStatus) {
        this.completionStatus = completionStatus;
    }

    public List<PlanProjectCompletion> getPlanProjectCompletions() {
        return planProjectCompletions;
    }

    public void setPlanProjectCompletions(List<PlanProjectCompletion> planProjectCompletions) {
        this.planProjectCompletions = planProjectCompletions;
    }

    public PropagandaWorkPlan getPropagandaWorkPlan() {
        return propagandaWorkPlan;
    }

    public void setPropagandaWorkPlan(PropagandaWorkPlan propagandaWorkPlan) {
        this.propagandaWorkPlan = propagandaWorkPlan;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
