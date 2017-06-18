package com.anonymous.domain.organization;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WangZK on 2017/5/25.
 * 组织机构实体类
 */
@Entity
public class Organization implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//组织机构id

    private String name;//组织机构名称

    private String pid;//上级的组织机构id

    public Organization() {
    }

    public Organization(String organization_id) {
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
