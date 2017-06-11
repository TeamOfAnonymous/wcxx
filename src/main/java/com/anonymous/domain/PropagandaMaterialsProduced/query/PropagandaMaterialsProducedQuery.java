package com.anonymous.domain.PropagandaMaterialsProduced.query;

import com.anonymous.domain.PropagandaMaterialsProduced.ProductionProducedMethod;
import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;

/**
 * Created by huangMP on 2017/6/1.
 * decription : 宣传品（资料）制作申请查询条件
 */
public class PropagandaMaterialsProducedQuery {


    /**
     * 每页的信息条数
     */
    private int rows;

    /**
     * 第几页 注意：首页页码为 0
     */
    private int page;

    /**
     * 组合 PropagandaMaterialsProduced
     */
    private PropagandaMaterialsProduced propagandaMaterialsProduced;

    /**
     * 总费用最少值
     */
    private float minTotalCost;

    /**
     * 总费用最大值
     */
    private float maxTotalCost;

    /**
     * 制作方式
     */
    private ProductionProducedMethod productionMethod;

    public PropagandaMaterialsProducedQuery(){
    }

    public PropagandaMaterialsProduced getPropagandaMaterialsProduced() {
        return propagandaMaterialsProduced;
    }

    public void setPropagandaMaterialsProduced(PropagandaMaterialsProduced propagandaMaterialsProduced) {
        this.propagandaMaterialsProduced = propagandaMaterialsProduced;
    }

    public float getMinTotalCost() {
        return minTotalCost;
    }

    public void setMinTotalCost(float minTotalCost) {
        this.minTotalCost = minTotalCost;
    }

    public float getMaxTotalCost() {
        return maxTotalCost;
    }

    public void setMaxTotalCost(float maxTotalCost) {
        this.maxTotalCost = maxTotalCost;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ProductionProducedMethod getProductionMethod() {
        return productionMethod;
    }

    public void setProductionMethod(ProductionProducedMethod productionMethod) {
        this.productionMethod = productionMethod;
    }

    @Override
    public String toString() {
        return "PropagandaMaterialsProducedQuery{" +
                "rows=" + rows +
                ", page=" + page +
                ", propagandaMaterialsProduced=" + propagandaMaterialsProduced +
                ", minTotalCost=" + minTotalCost +
                ", maxTotalCost=" + maxTotalCost +
                ", productionMethod=" + productionMethod +
                '}';
    }
}
