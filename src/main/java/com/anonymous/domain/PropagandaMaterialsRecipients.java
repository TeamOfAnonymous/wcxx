package com.anonymous.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 * 宣传物资领用
 */
@Entity
public class PropagandaMaterialsRecipients implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传物资领用id

    private LocalDate applicationDate;//申请时间
    private String title;//标题

    private String remarks;//备注
    private Integer approvalStatus;//审批状态，0为待审批，1为审核中，2为执行中，3为已完成，4为已归档

    @ManyToOne
    private User applicant;//申请人

    @OneToMany(mappedBy = "propagandaMaterialsRecipients",fetch = FetchType.EAGER)
    private List<PropagandaMaterials> propagandaMaterials = new ArrayList<>();//宣传物资


    public PropagandaMaterialsRecipients() {
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

    public List<PropagandaMaterials> getPropagandaMaterials() {
        return propagandaMaterials;
    }

    public void setPropagandaMaterials(List<PropagandaMaterials> propagandaMaterials) {
        this.propagandaMaterials = propagandaMaterials;
    }

}
