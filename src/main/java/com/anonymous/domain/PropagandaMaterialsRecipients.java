package com.anonymous.domain;

import javax.persistence.*;
import java.io.Serializable;
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

    private LocalDateTime applicationDate;//申请时间
    private String title;//标题

    private String remarks;//备注
    private Integer approvalStatus;//审批状态，0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档

    @ManyToOne
    private User applicant;//申请人

    @OneToMany(mappedBy = "propagandaMaterialsRecipients")
    private List<PropagandaMaterials> propagandaMaterials = new ArrayList<>();//宣传物资

    @ManyToOne
    private PropagandaInformation propagandaInformation;//所属宣传信息申请

    public PropagandaMaterialsRecipients() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
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

    public PropagandaInformation getPropagandaInformation() {
        return propagandaInformation;
    }

    public void setPropagandaInformation(PropagandaInformation propagandaInformation) {
        this.propagandaInformation = propagandaInformation;
    }
}
