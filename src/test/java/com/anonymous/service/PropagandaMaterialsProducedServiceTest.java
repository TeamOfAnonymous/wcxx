package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsProduced;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by huangMP on 2017/5/27.
 * decription :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagandaMaterialsProducedServiceTest {

    private PropagandaMaterialsProducedService propagandaMaterialsProducedService;

    public PropagandaMaterialsProducedServiceTest(@Autowired PropagandaMaterialsProducedService propagandaMaterialsProducedService ){
        this.propagandaMaterialsProducedService = propagandaMaterialsProducedService ;
    }



    public void testFindById() {

    }

    public void testAdd() {

    }

    public void testApply() {

    }

}