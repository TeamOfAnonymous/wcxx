package com.anonymous.service.inter;


import com.anonymous.service.PpgdaInfStatisticsService;

import java.time.LocalDate;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/8.
 */
public interface PpgdaInfStatisticsServiceInter {

	/**
	 * 获得统计数据
	 */
	PpgdaInfStatisticsService.StatisticsData getStatisticsData (LocalDate startDate, LocalDate endDate);

}
