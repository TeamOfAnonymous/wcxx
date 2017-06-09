package com.anonymous.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

/**
 * Description：宣传信息发布统计
 * Created by Peivxuan on 2017/6/7.
 */
@Entity
public class PpgdaInfStatistics {

	@Id
	@GeneratedValue(generator = "UUID2_GENERATOR")
	private String id;// 宣传信息发布统计id

	/**
	 * 创建的用户
	 */
	private String userId;

	/**
	 *  父类别
	 */
	private String mainCategoryId;

	/**
	 * 创建时间
	 */
	private LocalDate createTime;

	/**
	 * 各点总计
	 */
	private Integer counts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(String mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public LocalDate getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		return "PpgdaInfStatistics{" +
				"id='" + id + '\'' +
				", userId='" + userId + '\'' +
				", mainCategoryId='" + mainCategoryId + '\'' +
				", createTime=" + createTime +
				", counts=" + counts +
				'}';
	}
}
