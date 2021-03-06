package com.anonymous.domain.PropagandaMaterialsProduced;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by WangZK on 2017/5/26.
 * 宣传品（资料）内容
 */
@Entity
public class PropagandaMaterialsContent implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2_GENERATOR")
    private String id;//宣传品（资料）内容 id

    @NotNull
    @Enumerated(EnumType.STRING)
    private PromotionalCategory promotionalCategory;//宣传品类别

    private String name;//宣传品名称

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductionProducedMethod productionMethod;//制作方式
    private LocalDate requestCompletionTime;//要求完成时间
    private String specification;//规格
    private String unit;//单位
    private int productionQuantity;//制作数量
    private String advertisingCompanyName;//广告公司名称
    private String advertisingCompanyContactPerson;//广告公司联系人
    private String advertisingCompanyContactInformation;//广告公司联系方式
    private float cost;//费用

    @ManyToOne
    @JsonIgnore
    private PropagandaMaterialsProduced propagandaMaterialsProduced;//所属宣传品（资料）制作

    public PropagandaMaterialsContent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PromotionalCategory getPromotionalCategory() {
        return promotionalCategory;
    }

    public void setPromotionalCategory(PromotionalCategory promotionalCategory) {
        this.promotionalCategory = promotionalCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductionProducedMethod getProductionMethod() {
        return productionMethod;
    }

    public void setProductionMethod(ProductionProducedMethod productionMethod) {
        this.productionMethod = productionMethod;
    }

    public LocalDate getRequestCompletionTime() {
        return requestCompletionTime;
    }

    public void setRequestCompletionTime(LocalDate requestCompletionTime) {
        this.requestCompletionTime = requestCompletionTime;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getProductionQuantity() {
        return productionQuantity;
    }

    public void setProductionQuantity(int productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    public String getAdvertisingCompanyName() {
        return advertisingCompanyName;
    }

    public void setAdvertisingCompanyName(String advertisingCompanyName) {
        this.advertisingCompanyName = advertisingCompanyName;
    }

    public String getAdvertisingCompanyContactPerson() {
        return advertisingCompanyContactPerson;
    }

    public void setAdvertisingCompanyContactPerson(String advertisingCompanyContactPerson) {
        this.advertisingCompanyContactPerson = advertisingCompanyContactPerson;
    }

    public String getAdvertisingCompanyContactInformation() {
        return advertisingCompanyContactInformation;
    }

    public void setAdvertisingCompanyContactInformation(String advertisingCompanyContactInformation) {
        this.advertisingCompanyContactInformation = advertisingCompanyContactInformation;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public PropagandaMaterialsProduced getPropagandaMaterialsProduced() {
        return propagandaMaterialsProduced;
    }

    public void setPropagandaMaterialsProduced(PropagandaMaterialsProduced propagandaMaterialsProduced) {
        this.propagandaMaterialsProduced = propagandaMaterialsProduced;
    }
}
