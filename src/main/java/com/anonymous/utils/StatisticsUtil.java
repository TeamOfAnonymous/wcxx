package com.anonymous.utils;

import com.anonymous.domain.User;

/**
 * Created by Administrator on 2017/6/6.
 * 统计工具类
 * 方便将统计信息传到前端
 */
public class StatisticsUtil {

    private User user;//申请人，领用人

    private int brochureNum = 0;//宣传手册数量
    private int badgeNum = 0;//纪念胸章数量
    private int pendantNum = 0;//样品吊坠数量
    private int cinemaTicketNum = 0;//电影票数量

    private int other;//其他

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBrochureNum() {
        return brochureNum;
    }

    public void setBrochureNum(int brochureNum) {
        this.brochureNum = brochureNum;
    }

    public int getBadgeNum() {
        return badgeNum;
    }

    public void setBadgeNum(int badgeNum) {
        this.badgeNum = badgeNum;
    }

    public int getPendantNum() {
        return pendantNum;
    }

    public void setPendantNum(int pendantNum) {
        this.pendantNum = pendantNum;
    }

    public int getCinemaTicketNum() {
        return cinemaTicketNum;
    }

    public void setCinemaTicketNum(int cinemaTicketNum) {
        this.cinemaTicketNum = cinemaTicketNum;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}
