package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation.CategoryTree;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaInformationCategoryServiceTest {

	@Autowired
	private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;

	@Test
	public void getCategoriesOfTree() throws Exception {
		//CategoryTree categoriesOfTree = propagandaInformationCategoryService.getCategoriesOfTree("", "");
		//System.out.println(categoriesOfTree);
	}

}