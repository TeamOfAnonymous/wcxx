package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaInformationCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/6/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaInformationCategoryServiceInterTest {

    @Autowired
    private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;

    @Test
    public void add() throws Exception {

        PropagandaInformationCategory propagandaInformationCategory1 = new PropagandaInformationCategory();
        PropagandaInformationCategory propagandaInformationCategory2 = new PropagandaInformationCategory();
        PropagandaInformationCategory propagandaInformationCategory3 = new PropagandaInformationCategory();

        propagandaInformationCategory1.setName("电视媒体");
        propagandaInformationCategory1.setStatus(1);
        propagandaInformationCategory1 = propagandaInformationCategoryService.add(propagandaInformationCategory1);

        propagandaInformationCategory2.setName("广东卫视");
        propagandaInformationCategory2.setStatus(1);
        propagandaInformationCategory2.setPid(propagandaInformationCategory1.getId());
        propagandaInformationCategory2 = propagandaInformationCategoryService.add(propagandaInformationCategory2);

        propagandaInformationCategory3.setName("南方卫视");
        propagandaInformationCategory3.setStatus(1);
        propagandaInformationCategory3.setPid(propagandaInformationCategory1.getId());
        propagandaInformationCategory3 = propagandaInformationCategoryService.add(propagandaInformationCategory3);



    }

    @Test
    public void findByIndistinctName() throws Exception {
        List<PropagandaInformationCategory> categories = propagandaInformationCategoryService.findByIndistinctName("电视媒体");
        System.out.println("电视媒体："+categories.get(0).getName());

        categories = propagandaInformationCategoryService.findByIndistinctName("卫视");
        System.out.println("卫视："+categories.get(0).getName());
        System.out.println("卫视："+categories.get(1).getName());
    }

}