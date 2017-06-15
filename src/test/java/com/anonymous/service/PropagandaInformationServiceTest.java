package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation;
import com.anonymous.domain.PropagandaInformationCategory;
import com.anonymous.domain.User;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaInformationServiceTest {

	@Autowired
	private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;

	@Autowired
	private PropagandaInformationServiceInter propagandaInformationService;

	@Test
	public void save() throws Exception {
		//创建一个宣传分类对象1
		PropagandaInformationCategory propagandaInformationCategoryOne = new PropagandaInformationCategory();
		//创建一个宣传分类对象2
		PropagandaInformationCategory propagandaInformationCategoryTwo = new PropagandaInformationCategory();
		propagandaInformationCategoryOne.setName("第一分类");
		propagandaInformationCategoryTwo.setName("第二分类");
		propagandaInformationCategoryOne = propagandaInformationCategoryService.add(propagandaInformationCategoryOne);
		propagandaInformationCategoryTwo = propagandaInformationCategoryService.add(propagandaInformationCategoryTwo);

		List<PropagandaInformationCategory> propagandaInformationCategories = new ArrayList<>();
		propagandaInformationCategories.add(propagandaInformationCategoryOne);
		propagandaInformationCategories.add(propagandaInformationCategoryTwo);

		User user1 = new User();
		user1.setId("qweqwe");

		User user2 = new User();
		user2.setId("asdasd");

		//创建一个宣传信息对象1
		PropagandaInformation propagandaInformationOne = new PropagandaInformation();
		//创建一个宣传信息对象2
		PropagandaInformation propagandaInformationTwo = new PropagandaInformation();
		propagandaInformationOne.setTitle("第一个宣传信息对象");
		propagandaInformationOne.setApplicant(user1);
		propagandaInformationOne.setApplicationDate(LocalDateTime.now());
		propagandaInformationOne.setPropagandaInformationCategories(propagandaInformationCategories);
		propagandaInformationTwo.setTitle("第二个宣传信息对象");
		propagandaInformationTwo.setApplicant(user2);
		propagandaInformationTwo.setApplicationDate(LocalDateTime.now());
		propagandaInformationTwo.setPropagandaInformationCategories(propagandaInformationCategories);

		propagandaInformationService.save(propagandaInformationOne);
		propagandaInformationService.save(propagandaInformationTwo);

	}

}