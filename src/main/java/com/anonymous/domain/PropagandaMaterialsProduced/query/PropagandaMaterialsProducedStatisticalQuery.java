package com.anonymous.domain.PropagandaMaterialsProduced.query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by huangMP on 2017/6/6.
 * decription : 宣传品（资料）制作统计查询条件
 */
public class PropagandaMaterialsProducedStatisticalQuery {

    /**
     * 开始时期
     */
    private LocalDate startTime ;
    /**
     * 结束日期
     */
    private LocalDate endTime ;

    public PropagandaMaterialsProducedStatisticalQuery() {
    }

    public PropagandaMaterialsProducedStatisticalQuery(LocalDate startTime, LocalDate endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return LocalDateTime.of( this.startTime == null ? LocalDate.MIN : this.startTime , LocalTime.of(0,0) ) ;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        // 应该增加一天 不然没有包括边界值
        return LocalDateTime.of( ( this.endTime == null ? LocalDate.now() : this.endTime ) , LocalTime.of(0,0) ).plusDays(1) ;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}
