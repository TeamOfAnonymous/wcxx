package com.anonymous.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 * 宣传信息类别
 */
@Entity
public class PropagandaInformationCategory implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//类别id

    private String name;//类别名称
    private Integer status;//状态

    private LocalDateTime lastModifiedTime;//最后修改时间

    private String pid;//上一级的类别id

    @ManyToOne
    private User creator;//创建人

    @ManyToMany
    private List<PropagandaInformation> propagandaInformations = new ArrayList<>();

    public PropagandaInformationCategory() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
