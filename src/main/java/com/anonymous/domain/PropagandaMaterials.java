package com.anonymous.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by WangZK on 2017/5/26.
 * 宣传物资
 */
@Entity
public class PropagandaMaterials implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传物资id

    private String name;//物资名称
    private String unit;//单位
    private int quantity;//数量
    private String useDirection;//使用方向
    private String remarks;//备注

    @ManyToOne
    @JsonIgnore
    private PropagandaMaterialsRecipients propagandaMaterialsRecipients;//所属宣传物资领用

    public PropagandaMaterials() {
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUseDirection() {
        return useDirection;
    }

    public void setUseDirection(String useDirection) {
        this.useDirection = useDirection;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public PropagandaMaterialsRecipients getPropagandaMaterialsRecipients() {
        return propagandaMaterialsRecipients;
    }

    public void setPropagandaMaterialsRecipients(PropagandaMaterialsRecipients propagandaMaterialsRecipients) {
        this.propagandaMaterialsRecipients = propagandaMaterialsRecipients;
    }
}
