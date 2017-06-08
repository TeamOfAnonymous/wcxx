package com.anonymous.domain.PropagandaMaterialsProduced;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by huangMP on 2017/6/5.
 * decription : 宣传品 制作方式 : 内部制作, 广告公司制作
 */
public enum ProductionProducedMethod {

    ProducedInner("内部制作") , AdAgencyProduced("广告公司制作") ;

    private String name = "";

    ProductionProducedMethod(String name){
        this.name = name ;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name ;
    }
}
