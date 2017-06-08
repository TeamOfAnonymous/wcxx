package com.anonymous.domain.PropagandaMaterialsProduced;

import com.anonymous.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 * 宣传品（资料）制作
 */
@Entity
public class PropagandaMaterialsProduced implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传品（资料）制作 id

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime applicationDate ;//申请时间

    private String title ;//标题

    private float totalCost;//总费用

    @ManyToOne
    private User principal;//负责人

    private String remarks ;//备注

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;//审批状态，0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档

    @ManyToOne
    private User applicant;//申请人

    @OneToMany(mappedBy = "propagandaMaterialsProduced",fetch = FetchType.EAGER) // ,cascade = CascadeType.MERGE
    private List<PropagandaMaterialsContent> propagandaMaterialsContents = new ArrayList<>();//宣传品（资料）内容

/*    @ManyToOne
    private PropagandaInformation propagandaInformation;//所属宣传信息申请*/

    public PropagandaMaterialsProduced() {
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

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public List<PropagandaMaterialsContent> getPropagandaMaterialsContents() {
        return propagandaMaterialsContents;
    }

    public void setPropagandaMaterialsContents(List<PropagandaMaterialsContent> propagandaMaterialsContents) {
        this.propagandaMaterialsContents = propagandaMaterialsContents;
    }

/*    public PropagandaInformation getPropagandaInformation() {
        return propagandaInformation;
    }

    public void setPropagandaInformation(PropagandaInformation propagandaInformation) {
        this.propagandaInformation = propagandaInformation;
    }*/

    @Override
    public String toString() {
        return "PropagandaMaterialsProduced{" +
                "id='" + id + '\'' +
                ", applicationDate=" + applicationDate +
                ", title='" + title + '\'' +
                ", totalCost=" + totalCost +
                ", principal=" + principal +
                ", remarks='" + remarks + '\'' +
                ", approvalStatus=" + approvalStatus +
                ", applicant=" + applicant +
                ", propagandaMaterialsContents=" + propagandaMaterialsContents +
                '}';
    }
}
