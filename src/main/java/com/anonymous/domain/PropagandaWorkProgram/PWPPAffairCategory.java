package com.anonymous.domain.PropagandaWorkProgram;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by huangMP on 2017/6/20.
 * decription : 宣传工作方案子项目分类
 */
@Entity
public class PWPPAffairCategory {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id ;// id
    private String name ; // 分类名称
    private String fromHeaderFormat ; // from表单表头格式 json格式

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

    public String getFromHeaderFormat() {
        return fromHeaderFormat;
    }

    public void setFromHeaderFormat(String fromHeaderFormat) {
        this.fromHeaderFormat = fromHeaderFormat;
    }
}
