package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.domain.User;
import com.anonymous.repository.PropagandaInformationRepository;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private PropagandaInformationRepository propagandaInformationRepository;

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

//		User user2 = new User();
//		user2.setId("asdasd");

		//创建一个宣传信息对象1
		PropagandaInformation propagandaInformationOne = new PropagandaInformation();
		//创建一个宣传信息对象2
//		PropagandaInformation propagandaInformationTwo = new PropagandaInformation();
		propagandaInformationOne.setTitle("改过的第一个宣传信息对象");
		propagandaInformationOne.setId("561ddf3e-233f-4520-812b-87eda9f36b32");
		propagandaInformationOne.setApplicant(user1);
		propagandaInformationOne.setApplicationDate(LocalDate.now());
		propagandaInformationOne.setPropagandaInformationCategories(propagandaInformationCategories);
//		propagandaInformationTwo.setTitle("第二个宣传信息对象");
//		propagandaInformationTwo.setApplicant(user2);
//		propagandaInformationTwo.setApplicationDate(LocalDateTime.now());
//		propagandaInformationTwo.setPropagandaInformationCategories(propagandaInformationCategories);

		propagandaInformationService.save(propagandaInformationOne);
//		propagandaInformationService.save(propagandaInformationTwo);

	}

	@Test
	public void get() throws Exception {
		PropagandaInformation one = propagandaInformationRepository.findOne("e4fc5164-b57c-4e7a-a7eb-92d8dfa1b3aa");
		System.out.println(one.getPropagandaInformationCategories().get(0).getName());
	}

}