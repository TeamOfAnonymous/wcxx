package com.anonymous.domain.PropagandaWorkProgram;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by huangMP on 2017/6/18.
 * decription : 宣传工作方案分类
 */
@Entity
public class PWPCategory {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id ; // 主键id
    private String name ; // 分类名称

    public PWPCategory(){

    }

    public PWPCategory(String name) {
        this.name = name;
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
}

