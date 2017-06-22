package com.anonymous.domain.PropagandaInformation;

import com.anonymous.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 * 宣传信息
 */
@Entity
public class PropagandaInformation implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传信息id

    private LocalDate applicationDate;//申请时间
    private String title;//标题
    private LocalDate startDate;//信息发布开始时间
    private LocalDate endDate;//信息发布结束时间
    private String content;//内容
    private String remarks;//备注
    private Integer approvalStatus;//审批状态，0为待审批，1为审核中，2为执行中，3为已完成，4为已归档

    @ManyToOne
    private User applicant;//申请人

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PropagandaInformationCategory> propagandaInformationCategories = new ArrayList<>();//宣传信息类别

    public PropagandaInformation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public List<PropagandaInformationCategory> getPropagandaInformationCategories() {
        return propagandaInformationCategories;
    }

    public void setPropagandaInformationCategories(List<PropagandaInformationCategory> propagandaInformationCategories) {
        this.propagandaInformationCategories = propagandaInformationCategories;
    }
}
