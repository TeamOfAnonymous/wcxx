package com.anonymous.repository;

import com.anonymous.domain.PropagandaInformation.PpgdaInfStatistics;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PpgdaInfStatisticsRepositoryTest {

	@Autowired
	private PpgdaInfStatisticsRepository ppgdaInfStatisticsRepository;

	@Autowired
	private PropagandaInformationCategoryRepository propagandaInformationCategoryRepository;

	@Autowired
	private UserRepository userRepository;

	private Map<String, String> getNameMap() {
		List<PropagandaInformationCategory> mainCategoryList = propagandaInformationCategoryRepository.findByPidIsNull();
		List<User> userList = userRepository.findAll();
		Map<String, String> nameMap = new HashMap<>(mainCategoryList.size() + userList.size());
		for (PropagandaInformationCategory propagandaInformationCategory : mainCategoryList)
			nameMap.put(propagandaInformationCategory.getId(), propagandaInformationCategory.getName());
		for (User user : userList)
			nameMap.put(user.getId(), user.getName());
		return nameMap;
	}

	@Test
	public void statisticsByDate() throws Exception {
		LocalDate startDate = LocalDate.of(2017,6,3);
		LocalDate endDate = LocalDate.of(2017,6,27);

		List<PpgdaInfStatistics> ppgdaInfStatistics = ppgdaInfStatisticsRepository.statisticsByDate(startDate, endDate);
		List<String> users = new ArrayList<>();
		List<String> mainCategories = new ArrayList<>();
		Map<String, Integer> dataMap = new HashMap<>();
		Map<String, String> nameMap = this.getNameMap();
		for (PpgdaInfStatistics temp : ppgdaInfStatistics) {
			String user = nameMap.get(temp.getUserId());
			String mainCategory = nameMap.get(temp.getMainCategoryId());
			dataMap.put(user+mainCategory, temp.getCounts());
			if (!users.contains(user))
				users.add(user);
			if (!mainCategories.contains(mainCategory))
				mainCategories.add(mainCategory);
		}
		Map<String, List<Integer>> statisticsTable = new HashMap<>(users.size() + 1);
		List<Integer> columnSum = new ArrayList<>(mainCategories.size()+1);
		for (int i = 0; i < mainCategories.size()+1; i++)
			columnSum.add(0);

		for(String s : users) {
			List<Integer> data = new ArrayList<>(mainCategories.size()+1);
			Integer rowSum = 0;
			for (int i = 0; i < mainCategories.size(); i++) {
				Integer dot = dataMap.get(s + mainCategories.get(i));
				if (dot == null) {
					dot = 0;
					data.add(dot);
				}
				else {
					data.add(dot);
					rowSum += dot;
				}
				columnSum.set(i, columnSum.get(i) + dot);
			}
			data.add(rowSum);
			columnSum.set(columnSum.size()-1, columnSum.get(columnSum.size()-1) + rowSum);
			statisticsTable.put(s, data);
		}
		users.add("总数");
		mainCategories.add("总数");
		statisticsTable.put("总数", columnSum);

		System.out.println("dataMap:" + dataMap.size());
		System.out.println("statisticsTable:" + statisticsTable.size());
		for (PpgdaInfStatistics p: ppgdaInfStatistics)
			System.out.println(p.toString());

		for (String p: mainCategories)
			System.out.print(p+"  ");
		System.out.println();
		for(String s : users) {
			List<Integer> integers = statisticsTable.get(s);
			System.out.print(s + "  ");
			for (Integer i : integers)
				System.out.print(i+"  ");
			System.out.println();
		}

	}


}
