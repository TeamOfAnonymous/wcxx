package com.anonymous.web;

import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by WangZK on 2017/6/1.
 */
@Controller
@Api(value = "宣传信息类别管理", description = "提供宣传信息类别的 增删改查 服务")
public class PropagandaInformationCategoryController {

    @Autowired
    private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;
}
