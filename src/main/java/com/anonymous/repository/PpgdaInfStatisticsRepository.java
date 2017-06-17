package com.anonymous.repository;

import com.anonymous.domain.PropagandaInformation.PpgdaInfStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Description：宣传统计Repository
 * Created by Peivxuan on 2017/6/7.
 */
public interface PpgdaInfStatisticsRepository extends JpaRepository<PpgdaInfStatistics, String> {

	@Query(value = "SELECT id, userId, mainCategoryId, createTime, COUNT(1) AS counts " +
			"FROM PpgdaInfStatistics WHERE createTime >= ?1 AND createTime <= ?2 " +
			"GROUP BY userId, mainCategoryId", nativeQuery = true)
	List<PpgdaInfStatistics> statisticsByDate(
			LocalDate startDate,
			LocalDate endDate
	);

}
