package com.anonymous.service.inter;

import com.anonymous.domain.PpgdaInfStatistics;

import java.time.LocalDate;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/8.
 */
public interface PpgdaInfStatisticsServiceInter {

	/**
	 * 获得统计数据
	 */
	PpgdaInfStatistics getStatisticsData(LocalDate startDate, LocalDate endDate);

}
