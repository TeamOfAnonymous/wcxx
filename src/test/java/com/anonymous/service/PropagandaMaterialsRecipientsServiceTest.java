package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterials;
import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.domain.User;
import com.anonymous.repository.PropagandaMaterialsRepository;
import com.anonymous.repository.UserRepository;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import com.anonymous.service.inter.UserServiceInter;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaMaterialsRecipientsServiceTest {

    @Autowired
    private PropagandaMaterialsRecipientsServiceInter propagandaMaterialsRecipientsService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        PropagandaMaterialsRecipients propagandaMaterialsRecipients = new PropagandaMaterialsRecipients();
        propagandaMaterialsRecipients.setApplicationDate(LocalDate.now());//申请时间
        propagandaMaterialsRecipients.setTitle("宣传物资领用测试标题");//标题
        propagandaMaterialsRecipients.setRemarks("宣传物资领用测试备注");//备注
        propagandaMaterialsRecipients.setApplicant(userRepository.findAll().get(0));//申请人

        //宣传物资
        PropagandaMaterials propagandaMaterials1 = new PropagandaMaterials();
        propagandaMaterials1.setName("宣传测试手册");//物资名称
        propagandaMaterials1.setUnit("册");//单位
        propagandaMaterials1.setQuantity(20);//数量
        propagandaMaterials1.setUseDirection("单位企业");//使用方向
        propagandaMaterials1.setRemarks("宣传测试手册备注");//备注
        PropagandaMaterials propagandaMaterials2 = new PropagandaMaterials();
        propagandaMaterials2.setName("纪念测试胸章");//物资名称
        propagandaMaterials2.setUnit("个");//单位
        propagandaMaterials2.setQuantity(20);//数量
        propagandaMaterials2.setUseDirection("单位企业");//使用方向
        propagandaMaterials2.setRemarks("纪念测试胸章备注");//备注
        PropagandaMaterials propagandaMaterials3 = new PropagandaMaterials();
        propagandaMaterials3.setName("样品测试吊坠");//物资名称
        propagandaMaterials3.setUnit("个");//单位
        propagandaMaterials3.setQuantity(20);//数量
        propagandaMaterials3.setUseDirection("单位企业");//使用方向
        propagandaMaterials3.setRemarks("样品测试吊坠备注");//备注
        propagandaMaterialsRecipients.getPropagandaMaterials().add(propagandaMaterials1);
        propagandaMaterialsRecipients.getPropagandaMaterials().add(propagandaMaterials2);
        propagandaMaterialsRecipients.getPropagandaMaterials().add(propagandaMaterials3);

        propagandaMaterialsRecipients = propagandaMaterialsRecipientsService.add(propagandaMaterialsRecipients);

        //判断id是否存在，存在则保存成功
        Assert.assertNotNull(propagandaMaterialsRecipients.getId());
        //判断标题是否存在，存在则保存成功
        Assert.assertNotNull(propagandaMaterialsRecipients.getTitle());
        //判断申请人是否存在，存在则保存成功
        Assert.assertNotNull(propagandaMaterialsRecipients.getApplicant());
        //判断日期是否一样，是则保存成功
        Assert.assertEquals(propagandaMaterialsRecipients.getApplicationDate(), LocalDate.now());
        //判断备注是否存在，存在则保存成功
        Assert.assertNotNull(propagandaMaterialsRecipients.getRemarks());
        //判断宣传物资是不是三个，是则保存成功
        Assert.assertTrue(propagandaMaterialsRecipients.getPropagandaMaterials().size() == 3);
    }

    @Test
    public void testGet() {
        String id = "836f7788-b9b7-464a-8669-07e9479ae5af";
        PropagandaMaterialsRecipients propagandaMaterialsRecipients = propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipients(id);

        //判断查询出来的id是否一致
        Assert.assertEquals(propagandaMaterialsRecipients.getId(), id);
    }

    @Test
    public void testGetForPage() {
        Integer currentPage = 0;
        Integer size = 10;
        String title = "";
        String applicant = "";
        LocalDate applicationDate = null;
        Integer approvalStatus = 100;
        Page<PropagandaMaterialsRecipients> propagandaMaterialsRecipients = propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipientsForPage(currentPage, size, title, applicant, applicationDate, approvalStatus);

    }

    @Test
    public void testDeletedSuccess() {
        String[] ids = {"5a291f24-171f-48b4-9df0-b7965316f666","6eec96ef-7a23-4dc6-b9f4-4f5112204c1b"};
        boolean result = propagandaMaterialsRecipientsService.deletedPropagandaMaterialsRecipients(ids);

        Assert.assertTrue("删除成功",result);
    }

    @Test
    public void testDeletedFail() {
        String[] ids = {"836f7788-b9b7-464a-8669-07e9479ae5af","3a2d4f8e-869c-4337-9199-8e1f3c23a1a3"};
        boolean result = propagandaMaterialsRecipientsService.deletedPropagandaMaterialsRecipients(ids);

        Assert.assertFalse("删除失败",result);
    }


    @Test
    public void testFileSuccess() {
        String[] ids = {"5a291f24-171f-48b4-9df0-b7965316f666","6eec96ef-7a23-4dc6-b9f4-4f5112204c1b"};
        boolean result = propagandaMaterialsRecipientsService.filePropagandaMaterialsRecipients(ids);

        Assert.assertTrue("归档成功",result);
    }

    @Test
    public void testFileFail() {
        String[] ids = {"836f7788-b9b7-464a-8669-07e9479ae5af","3a2d4f8e-869c-4337-9199-8e1f3c23a1a3"};
        boolean result = propagandaMaterialsRecipientsService.filePropagandaMaterialsRecipients(ids);

        Assert.assertFalse("归档失败",result);
    }
}
