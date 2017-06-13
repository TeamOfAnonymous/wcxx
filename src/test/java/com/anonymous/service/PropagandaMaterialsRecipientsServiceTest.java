package com.anonymous.service;

import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaMaterialsRecipientsServiceTest {

    @Autowired
    private PropagandaMaterialsRecipientsServiceInter propagandaMaterialsRecipientsService;

    @Test
    public void testGetPropagandaMaterialsRecipientsStatisticsForm() {
        LocalDate startDate = LocalDate.of(2017, 6, 1);
        LocalDate endDate = LocalDate.of(2017, 6, 10);
        propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipientsStatisticsForm(startDate, endDate);
    }
}
