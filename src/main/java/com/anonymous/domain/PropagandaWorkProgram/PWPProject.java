package com.anonymous.domain.PropagandaWorkProgram;

import com.anonymous.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by huangMP on 2017/6/20.
 * decription : 宣传工作方案子项目
 */
@Entity
public class PWPProject {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id ; // 主键id

    @OneToOne
    private PWPPAffairCategory affairCategory ; // 事务类别
    private String content ; // 内容及要求
    @ManyToOne
    private User principal; // 负责人

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate finishDate ; // 要求完成时间
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate latestReportingDate ; // 最新填报时间

    private int currentCondition ; // 当前完成进度 0~100

    private String fromDataContent ; // 表单具体内容 json格式保存

    @ManyToOne
    private PropagandaWorkProgram pwp ; // 上级宣传工作方案

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PWPPAffairCategory getAffairCategory() {
        return affairCategory;
    }

    public void setAffairCategory(PWPPAffairCategory affairCategory) {
        this.affairCategory = affairCategory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getLatestReportingDate() {
        return latestReportingDate;
    }

    public void setLatestReportingDate(LocalDate latestReportingDate) {
        this.latestReportingDate = latestReportingDate;
    }

    public int getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(int currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getFromDataContent() {
        return fromDataContent;
    }

    public void setFromDataContent(String fromDataContent) {
        this.fromDataContent = fromDataContent;
    }

    public PropagandaWorkProgram getPwp() {
        return pwp;
    }

    public void setPwp(PropagandaWorkProgram pwp) {
        this.pwp = pwp;
    }
}
