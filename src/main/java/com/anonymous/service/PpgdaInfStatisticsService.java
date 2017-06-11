package com.anonymous.service;

import com.anonymous.domain.PpgdaInfStatistics;
import com.anonymous.domain.PropagandaInformationCategory;
import com.anonymous.domain.User;
import com.anonymous.repository.PpgdaInfStatisticsRepository;
import com.anonymous.repository.PropagandaInformationCategoryRepository;
import com.anonymous.repository.UserRepository;
import com.anonymous.service.inter.PpgdaInfStatisticsServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/8.
 */
@Service
public class PpgdaInfStatisticsService implements PpgdaInfStatisticsServiceInter {

	@Autowired
	private PpgdaInfStatisticsRepository ppgdaInfStatisticsRepository;

	@Autowired
	private PropagandaInformationCategoryRepository propagandaInformationCategoryRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 将名字所对应的id替换为名字
	 */
	private Map<String, String> getNameMap() {
		//取得类别父节点
		List<PropagandaInformationCategory> mainCategoryList = propagandaInformationCategoryRepository.findByPidIsNull();
		//取得用户列表
		List<User> userList = userRepository.findAll();
		Map<String, String> nameMap = new HashMap<>(mainCategoryList.size() + userList.size());
		//将其保存到map中，方便替换
		for (PropagandaInformationCategory propagandaInformationCategory : mainCategoryList)
			nameMap.put(propagandaInformationCategory.getId(), propagandaInformationCategory.getName());
		for (User user : userList)
			nameMap.put(user.getId(), user.getName());
		return nameMap;
	}

	/**
	 * 获得统计数据
	 * @param startDate
	 * @param endDate
	 */
	@Override
	public StatisticsData getStatisticsData (LocalDate startDate, LocalDate endDate) {
		//获得未处理的统计数据
		List<PpgdaInfStatistics> ppgdaInfStatistics = ppgdaInfStatisticsRepository.statisticsByDate(startDate, endDate);
		//创建保存用户id的列表
		List<String> users = new ArrayList<>();
		//创建保存父类别的列表
		List<String> mainCategories = new ArrayList<>();
		//创建保存统计数据的map
		Map<String, Integer> dataMap = new HashMap<>();
		//得到保存用户名以及父类别名的map
		Map<String, String> nameMap = this.getNameMap();
		//遍历未处理统计数据
		for (PpgdaInfStatistics temp : ppgdaInfStatistics) {
			//将用户id替换为名称
			String user = nameMap.get(temp.getUserId());
			//将父类别id替换为名称
			String mainCategory = nameMap.get(temp.getMainCategoryId());
			//以用户名+父类别名作为key，将统计数据数据分别存储到dataMap中
			dataMap.put(user+mainCategory, temp.getCounts());
			//得到不包含重复用户名的列表
			if (!users.contains(user))
				users.add(user);
			//得到不包含重复父类别名的列表
			if (!mainCategories.contains(mainCategory))
				mainCategories.add(mainCategory);
		}
		//建立统计数据map
		Map<String, List<Integer>> statisticsMap = new LinkedHashMap<>(users.size() + 1);
		//建立列总计列表
		List<Integer> columnSum = new ArrayList<>(mainCategories.size()+1);
		//初始化列总计列表
		for (int i = 0; i < mainCategories.size()+1; i++)
			columnSum.add(0);
		//该统计map有users.size()个行，mainCategories.size()个列
		for(String s : users) {
			//创建保存单行数据的列表
			List<Integer> data = new ArrayList<>(mainCategories.size()+1);
			//将单行总计初始化为 0
			Integer rowSum = 0;
			//对此行进行封装
			for (int i = 0; i < mainCategories.size(); i++) {
				//通过上边规定的 用户名+父类别名 的key，从map中得到该数据点
				Integer dot = dataMap.get(s + mainCategories.get(i));
				//若为null则表示此人此项申请表数目为 0
				if (dot == null) {
					dot = 0;
					data.add(dot);
				}
				else {
					//将数据点保存到列中
					data.add(dot);
					//将此行的各点累加
					rowSum += dot;
				}
				//对各列数据点进行累加
				columnSum.set(i, columnSum.get(i) + dot);
			}
			//将行总数添加到该行列表尾
			data.add(rowSum);
			//将行总数进行累加
			columnSum.set(columnSum.size()-1, columnSum.get(columnSum.size()-1) + rowSum);
			//将此行数据添加到统计map中，以用户名为key
			statisticsMap.put(s, data);
		}
		//添加总计表头
		users.add("总数");
		//添加总计表头
		mainCategories.add("总数");
		//添加发布人
		mainCategories.add(0, "发布人");
		//将行总数添加到统计map中
		statisticsMap.put("总数", columnSum);

		return new StatisticsData(statisticsMap, users, mainCategories);
	}

	/**
	 *  数据返回类型
	 */
	public class StatisticsData{

		private Map<String, List<Integer>> statisticsMap;

		private List<String> users;

		private List<String> mainCategories;

		public StatisticsData(Map<String, List<Integer>> statisticsMap, List<String> users, List<String> mainCategories) {
			this.statisticsMap = statisticsMap;
			this.users = users;
			this.mainCategories = mainCategories;
		}

		public Map<String, List<Integer>> getStatisticsMap() {
			return statisticsMap;
		}

		public List<String> getUsers() {
			return users;
		}

		public List<String> getMainCategories() {
			return mainCategories;
		}
	}

}
