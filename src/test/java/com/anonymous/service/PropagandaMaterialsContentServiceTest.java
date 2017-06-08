package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsContent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by huangMP on 2017/5/27.
 * decription : 宣传品内容 Service层 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaMaterialsContentServiceTest {

    @Autowired
    private PropagandaMaterialsContentService propagandaMaterialsContentService;
    @Autowired
    private PropagandaMaterialsProducedService propagandaMaterialsProducedService;

    @Test
    public void testFindById() {
        PropagandaMaterialsContent p = propagandaMaterialsContentService.findById("b24fa852-c261-4459-ac92-1b66ab954cad");
        System.out.println(p.toString());
    }

    @Test
    public void testAdd() {
        PropagandaMaterialsContent propagandaMaterialsContent = new PropagandaMaterialsContent();
        propagandaMaterialsContent.setAdvertisingCompanyContactInformation("test2");
        propagandaMaterialsContent.setAdvertisingCompanyContactPerson("test2");
        propagandaMaterialsContent.setAdvertisingCompanyName("test2");
        propagandaMaterialsContent.setCost(20);
        propagandaMaterialsContent.setName("test2");
        propagandaMaterialsContent.setProductionQuantity(10);
        propagandaMaterialsContent.setPropagandaMaterialsProduced(propagandaMaterialsProducedService.findById("d109fd38-e224-43b7-bafd-8c80d6e62c38"));
        propagandaMaterialsContentService.add(propagandaMaterialsContent);
        Assert.assertNotNull(propagandaMaterialsContent.getId());
    }

    public void testApply() {

    }

}