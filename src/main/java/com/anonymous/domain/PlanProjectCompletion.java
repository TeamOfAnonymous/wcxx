package com.anonymous.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/6/3.
 * 宣传工作计划项目完成情况
 */
@Entity
public class PlanProjectCompletion implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传工作计划项目完成情况id

    private String completion;//完成情况
    private String schedule;//进度

    @ManyToOne
    private PlanProject planProject;//所属宣传工作计划项目

    public PlanProjectCompletion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public PlanProject getPlanProject() {
        return planProject;
    }

    public void setPlanProject(PlanProject planProject) {
        this.planProject = planProject;
    }
}
