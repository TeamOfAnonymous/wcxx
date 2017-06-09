package com.anonymous.repository;

import com.anonymous.domain.PpgdaInfStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Description：宣传统计Repository
 * Created by Peivxuan on 2017/6/7.
 */
public interface PpgdaInfStatisticsRepository extends JpaRepository<PpgdaInfStatistics, String> {

	@Query(value = "SELECT statistics.userId, statistics.mainCategoryId, COUNT(statistics.mainCategoryId) AS counts " +
			"FROM PpgdaInfStatistics statistics WHERE statistics.createTime >= :startDate AND statistics.createTime <= :endDate " +
			"GROUP BY userId, mainCategoryId")
	List<PpgdaInfStatistics> statisticsByDate(
			@Param(value = "startDate") LocalDate startDate,
			@Param(value = "endDate")LocalDate endDate
	);

}
