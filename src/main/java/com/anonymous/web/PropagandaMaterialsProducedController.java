package com.anonymous.web;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedStatisticalQuery;
import com.anonymous.service.inter.PropagandaMaterialsProducedServiceInter;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription :宣传品制作申请 Controller
 */
@Controller
// @RequestMapping("materialsProduced")
@Api(value="宣传品（资料）制作申请",description = "提供 宣传品制作申请 的 增删改查 服务 ")
public class PropagandaMaterialsProducedController {

    @Autowired
    private PropagandaMaterialsProducedServiceInter propagandaMaterialsProducedService;

    @PostMapping(value = "addPropagandaMaterialsProduced")
    @ApiOperation(value = "添加申请到草稿箱")
    @ResponseBody
    public PropagandaMaterialsProduced add(@RequestBody PropagandaMaterialsProduced propagandaMaterialsProduced ) {
        System.out.println(propagandaMaterialsProduced.toString());
        return propagandaMaterialsProducedService.add(propagandaMaterialsProduced );
    }

    @PutMapping(value = "applyPropagandaMaterialsProduced")
    @ApiOperation(value = "提交申请")
    @ResponseBody
    public PropagandaMaterialsProduced apply(@RequestParam String id) {
        return propagandaMaterialsProducedService.apply(id);
    }

    @GetMapping(value = "getPropagandaMaterialsProduced")
    @ApiOperation(value = "通过id查找申请")
    @ResponseBody
    public PropagandaMaterialsProduced find(@RequestParam String id) {
        return propagandaMaterialsProducedService.findById(id);
    }

    /**
     * 请求格式
         {
         "minTotalCost": 0,
         "maxTotalCost": 100,
         "page": 0,
         "rows": 10,
             "propagandaMaterialsProduced": {
                 "title": "itl",
                 "approvalStatus": 0,
                 "productionMethod": "广告公司制作",
                 "applicant": {
                    "name": ""
                 }
             }
         }
     * @param query
     * @return
     */
    @RequestMapping(value = "propagandaMaterialsProducedQuery", method = RequestMethod.POST)
    @ApiOperation(value = "业务查询 : 宣传品（资料）制作查询")
    @ResponseBody
    public Page propagandaMaterialsProducedQuery(@RequestBody PropagandaMaterialsProducedQuery query) {
        return propagandaMaterialsProducedService.findByMultiExample(query);
    }

    /**
     * 请求格式
     {
     "startTime": "2010-06-02"，
     "endTime":"2019-06-02"
     }
     * @param query
     * @return
     */
    @PostMapping(value = "statisticalQueryForPropagandaMaterialsProduced")
    @ApiOperation(value = "业务查询 : 宣传品（资料）制作申请统计查询")
    @ResponseBody
    public List<List<String>>  statisticalQuery(@RequestBody PropagandaMaterialsProducedStatisticalQuery query) {
        return propagandaMaterialsProducedService.statisticalQuery(query);
    }
}
