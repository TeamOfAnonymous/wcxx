package com.anonymous.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by WangZK on 2017/6/3.
 * 宣传方案项目完成情况
 */
@Entity
public class ProgramProjectCompletion implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传方案项目完成情况id

    private String completion;//完成情况
    private String schedule;//进度

    @ManyToOne
    private ProgramProject programProject;//所属宣传方案项目

    public ProgramProjectCompletion() {
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

    public ProgramProject getProgramProject() {
        return programProject;
    }

    public void setProgramProject(ProgramProject programProject) {
        this.programProject = programProject;
    }
}
