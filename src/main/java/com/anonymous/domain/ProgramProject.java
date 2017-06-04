package com.anonymous.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/6/3.
 * 宣传方案项目
 */
@Entity
public class ProgramProject implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传工作计划项目id

    private String content;//具体内容

    private LocalDate requestCompletionTime;//要求完成时间

    @ManyToOne
    private User principal;//负责人

    private Integer completionStatus;//完成状态，0为未完成，1为已完成

    private String subContent;//主要内容

    @ManyToOne
    private ProgramProjectCategory programProjectCategory;//所属宣传方案项目类别

    @ManyToOne
    private PropagandaWorkProgram propagandaWorkProgram;

    @OneToMany(mappedBy = "programProject")
    private List<ProgramProjectCompletion> programProjectCompletions = new ArrayList<>();

    public ProgramProject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getRequestCompletionTime() {
        return requestCompletionTime;
    }

    public void setRequestCompletionTime(LocalDate requestCompletionTime) {
        this.requestCompletionTime = requestCompletionTime;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public Integer getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(Integer completionStatus) {
        this.completionStatus = completionStatus;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public ProgramProjectCategory getProgramProjectCategory() {
        return programProjectCategory;
    }

    public void setProgramProjectCategory(ProgramProjectCategory programProjectCategory) {
        this.programProjectCategory = programProjectCategory;
    }

    public PropagandaWorkProgram getPropagandaWorkProgram() {
        return propagandaWorkProgram;
    }

    public void setPropagandaWorkProgram(PropagandaWorkProgram propagandaWorkProgram) {
        this.propagandaWorkProgram = propagandaWorkProgram;
    }

    public List<ProgramProjectCompletion> getProgramProjectCompletions() {
        return programProjectCompletions;
    }

    public void setProgramProjectCompletions(List<ProgramProjectCompletion> programProjectCompletions) {
        this.programProjectCompletions = programProjectCompletions;
    }
}
