package com.anonymous.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by WangZK on 2017/6/8.
 * 宣传工作总结
 */
@Entity
public class PropagandaWorkSummary implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传工作总结id

    private LocalDateTime draftDate;//拟稿时间

    private String title;//标题

    @ManyToOne
    private User draftMan;//拟稿人

    private String content;//总结内容

    private Integer approvalStatus;//审批状态，0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档

    public PropagandaWorkSummary() {
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

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
