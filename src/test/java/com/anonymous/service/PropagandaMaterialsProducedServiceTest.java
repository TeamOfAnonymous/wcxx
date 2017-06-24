package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsProduced.*;
import com.anonymous.domain.PropagandaMaterialsProduced.dto.PropagandaMaterialsProducedStatisticalDto;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedStatisticalQuery;
import com.anonymous.domain.User;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

/**
 * Created by huangMP on 2017/5/27.
 * decription :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaMaterialsProducedServiceTest {


    @Autowired
    private PropagandaMaterialsContentService propagandaMaterialsContentService;
    @Autowired
    private PropagandaMaterialsProducedService propagandaMaterialsProducedService;
    @Autowired
    private UserService userService;

    @Test
    public void testFindById() {
        PropagandaMaterialsProduced p = propagandaMaterialsProducedService.findById("ba9724f0-e081-4c3a-9faa-8b795c975289");
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testAdd() {
        PropagandaMaterialsProduced p = new PropagandaMaterialsProduced();
        p.setTitle("title5");
        p.setRemarks("remarks5");
        p.setApprovalStatus(PMPApprovalStatus.Draft);
        p.setApplicant(userService.getUser("29d3d48d-f9e2-42cc-b362-93c70bf24dc5"));
        p.setPrincipal(userService.getUser("29d3d48d-f9e2-42cc-b362-93c70bf24dc5"));
        System.out.println("size = " + p.getPropagandaMaterialsContents().size());

        PropagandaMaterialsContent pc1 = new PropagandaMaterialsContent();
        pc1.setAdvertisingCompanyContactInformation("test9");
        pc1.setAdvertisingCompanyContactPerson("test9");
        pc1.setAdvertisingCompanyName("test9");
        pc1.setCost(20);
        pc1.setName("test9");
        pc1.setProductionMethod(ProductionProducedMethod.ProducedInner);
        pc1.setProductionQuantity(10);
        pc1.setPromotionalCategory(PromotionalCategory.Book);
        p.getPropagandaMaterialsContents().add(pc1);
        System.out.println(PromotionalCategory.Book);

        PropagandaMaterialsContent pc2 = new PropagandaMaterialsContent();
        pc2.setAdvertisingCompanyContactInformation("test10");
        pc2.setAdvertisingCompanyContactPerson("test10");
        pc2.setAdvertisingCompanyName("test10");
        pc2.setCost(20);
        pc2.setName("test10");
        pc2.setProductionMethod(ProductionProducedMethod.AdAgencyProduced);
        pc2.setProductionQuantity(10);
        pc2.setPromotionalCategory(PromotionalCategory.Other);
        p.getPropagandaMaterialsContents().add(pc2);

        propagandaMaterialsProducedService.add(p);
    }


    @Test
    public void testDeletes() {
        String[] ids = new String[]{"96b309f9-f274-453b-8077-00584d2dc04e"};
        boolean result = propagandaMaterialsProducedService.delete( ids );
        System.out.println( "result = " + result );
    }

    @Test
    public void testApply() {
        PropagandaMaterialsProduced p = propagandaMaterialsProducedService.apply("00e30cdf-3c80-4555-9c5a-e99a5fdc6921");
        Assert.assertEquals(PMPApprovalStatus.WaitForApproval , p.getApprovalStatus() );
    }

    @Test
    public void testFindByMultiExample() {
        PropagandaMaterialsProducedQuery query = new PropagandaMaterialsProducedQuery();
        PropagandaMaterialsProduced p = new PropagandaMaterialsProduced();

        query.setPropagandaMaterialsProduced(p);

        query.setMinTotalCost(0);
        query.setMaxTotalCost(100);

        query.setPage(0);
        query.setRows(100);

//        query.setProductionMethod(ProductionProducedMethod.ProducedInner);

        // 通过宣传品标题
        p.setTitle("tle");

        // 通过宣传品申请人姓名查找
        User user = new User();
        //       user.setName("小");
        user.setName("");
        p.setApplicant(user);

        // 通过 状态
//        p.setApprovalStatus(PMPApprovalStatus.Draft);

        System.out.println(query.toString());

        Page<PropagandaMaterialsProduced> page = propagandaMaterialsProducedService.findByMultiExample(query);

        System.out.println("getTotalElements " + page.getTotalElements());
        System.out.println("getTotalPages " + page.getTotalPages());
        System.out.println("getNumberOfElements " + page.getNumberOfElements());
        System.out.println("getNumber " + page.getNumber());
        System.out.println("getSize " + page.getSize());
    }


    @Test
    public void testFindByQuery(){
        PropagandaMaterialsProducedQuery query = new PropagandaMaterialsProducedQuery();
        PropagandaMaterialsProduced p = new PropagandaMaterialsProduced();

        query.setPropagandaMaterialsProduced(p);

        query.setMinTotalCost(0);
        query.setMaxTotalCost(100);

        query.setPage(0);
        query.setRows(100);

//        query.setProductionMethod(ProductionProducedMethod.ProducedInner);

        // 通过宣传品标题
        p.setTitle("tle");

        // 通过宣传品申请人姓名查找
        User user = new User();
 //       user.setName("小");
        user.setName("");
        p.setApplicant(user);

        // 通过 状态
//        p.setApprovalStatus(PMPApprovalStatus.Draft);

        System.out.println(query.toString());

        Page<PropagandaMaterialsProduced> page = propagandaMaterialsProducedService.findByQuery(query);

        System.out.println("getTotalElements " + page.getTotalElements());
        System.out.println("getTotalPages " + page.getTotalPages());
        System.out.println("getNumberOfElements " + page.getNumberOfElements());
        System.out.println("getNumber " + page.getNumber());
        System.out.println("getSize " + page.getSize());
    }

    @Test
    public void testFindBySpecification(){
        PropagandaMaterialsProducedQuery query = new PropagandaMaterialsProducedQuery();
        PropagandaMaterialsProduced p = new PropagandaMaterialsProduced();

        query.setPropagandaMaterialsProduced(p);

        query.setMinTotalCost(0);
        query.setMaxTotalCost(100);

        query.setPage(1);
        query.setRows(3);

//        query.setProductionMethod(ProductionProducedMethod.ProducedInner);

        // 通过宣传品标题
        p.setTitle("tle");

        // 通过宣传品申请人姓名查找
        User user = new User();
        //       user.setName("小");
        user.setName("");
        p.setApplicant(user);

        // 通过 状态
//        p.setApprovalStatus(PMPApprovalStatus.Draft);

        System.out.println(query.toString());

        Page<PropagandaMaterialsProduced> page = propagandaMaterialsProducedService.findBySpecification(query);

        System.out.println("getTotalElements " + page.getTotalElements());
        System.out.println("getTotalPages " + page.getTotalPages());
        System.out.println("getNumberOfElements " + page.getNumberOfElements());
        System.out.println("getNumber " + page.getNumber());
        System.out.println("getSize " + page.getSize());
    }

    @Test
    public void testFindStatisticalQuery(){
        PropagandaMaterialsProducedStatisticalQuery query = new PropagandaMaterialsProducedStatisticalQuery(
                LocalDate.of( 2011, 10 , 4 ),
                LocalDate.of( 2019, 6 , 10 )
        );
        PropagandaMaterialsProducedStatisticalDto resultTable = propagandaMaterialsProducedService.statisticalQuery(query);

        // 遍历该 二维表
//        for( List<String> row : resultTable ){
//            for( String column : row){
//                System.out.print("\t " + column);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}