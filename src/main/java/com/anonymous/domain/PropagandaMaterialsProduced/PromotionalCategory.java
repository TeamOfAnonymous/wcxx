package com.anonymous.domain.PropagandaMaterialsProduced;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by huangMP on 2017/6/6.
 * decription : 宣传品类别 : 宣传（献血、知识）手册 , 电梯、海报广告 , 电视媒体视频（材料） , 宣传、活动（指引）单张 , 其他
 */
public enum PromotionalCategory {

    Book("宣传（献血、知识）手册") , Advert("电梯、海报广告") , Video("电视媒体视频（材料）") ,  Handbill("宣传、活动（指引）单张") , Other("其他") ;

    private String name ="";

    @JsonValue
    public String getName() {
        return name;
    }

    PromotionalCategory(String name ){
        this.name = name ;
    }

    @Override
    public String toString() {
        return this.name ;
    }
}
