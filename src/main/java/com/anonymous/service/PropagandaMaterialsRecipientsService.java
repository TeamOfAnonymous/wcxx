package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterials;
import com.anonymous.domain.PropagandaMaterialsProduced.ApprovalStatus;
import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.domain.User;
import com.anonymous.repository.PropagandaMaterialsRecipientsRepository;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import com.anonymous.service.inter.PropagandaMaterialsServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by WangZK on 2017/5/26.
 */
@Service
public class PropagandaMaterialsRecipientsService implements PropagandaMaterialsRecipientsServiceInter {

    @Autowired
    private PropagandaMaterialsRecipientsRepository propagandaMaterialsRecipientsRepository;
    @Autowired
    private PropagandaMaterialsServiceInter propagandaMaterialsService;

    @Override
    public PropagandaMaterialsRecipients add(PropagandaMaterialsRecipients propagandaMaterialsRecipients) {
        List<PropagandaMaterials> propagandaMaterialss = propagandaMaterialsRecipients.getPropagandaMaterials();

        propagandaMaterialsRecipients.setApprovalStatus(0);
        propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.save(propagandaMaterialsRecipients);

        for (PropagandaMaterials propagandaMaterials : propagandaMaterialss) {
            propagandaMaterials.setPropagandaMaterialsRecipients(propagandaMaterialsRecipients);
            propagandaMaterialsService.add(propagandaMaterials);
        }

        return propagandaMaterialsRecipients;
    }

    @Override
    public PropagandaMaterialsRecipients getPropagandaMaterialsRecipients(String id) {
        return propagandaMaterialsRecipientsRepository.findOne(id);
    }

    @Override
    public Page<PropagandaMaterialsRecipients> getPropagandaMaterialsRecipientsForPage(Integer currentPage, Integer size, String title, String applicant, LocalDate applicationDate, Integer approvalStatus) {
        Sort sort = new Sort(Sort.Direction.fromString("DESC"), "applicationDate");
        Pageable pageable = new PageRequest(currentPage, size, sort);

        if (approvalStatus == 100) {
            if ("".equals(applicationDate) || applicationDate == null) {
                return propagandaMaterialsRecipientsRepository.getPropagandaMaterialsRecipientsForPage(title, applicant, pageable);
            } else {
                return propagandaMaterialsRecipientsRepository.getPropagandaMaterialsRecipientsForPage(title, applicant, applicationDate, pageable);
            }
        } else {
            if ("".equals(applicationDate) || applicationDate == null) {
                return propagandaMaterialsRecipientsRepository.getPropagandaMaterialsRecipientsForPage(title, applicant, approvalStatus, pageable);
            } else {
                return propagandaMaterialsRecipientsRepository.getPropagandaMaterialsRecipientsForPage(title, applicant, applicationDate, approvalStatus, pageable);
            }

        }
    }

    //宣传物资领用统计
    @Override
    public Map<String, Object> getPropagandaMaterialsRecipientsByApplicationDate(LocalDate startDate, LocalDate endDate) {
        //获取时间段内所有的宣传物资领用申请
        List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients1 = findByApplicationDate(startDate, endDate);

        //获取所有的申请人
        Set<User> users = new HashSet<>();
        for (PropagandaMaterialsRecipients propagandaMaterialsRecipients : propagandaMaterialsRecipients1) {
            users.add(propagandaMaterialsRecipients.getApplicant());
        }
        //获取宣传物资的名称
        Set<String> propagandaMaterialsNames = getPropagandaMaterialsNames(propagandaMaterialsRecipients1);
        //填充表头
        List<String> heads = new ArrayList<>();
        heads.add("申请人");
        for (String name : propagandaMaterialsNames) {
            heads.add(name);
        }
        heads.add("合计");

        //将对应的宣传物资进行统计
        List<List<String>> statisticsResult = new ArrayList<>();
        for (User user : users) {
            List<String> result = new ArrayList<>();
            if (startDate == null || "".equals(startDate)) {
                propagandaMaterialsRecipients1 = propagandaMaterialsRecipientsRepository.findByApplicant(user.getId());
            } else if (endDate == null || "".equals(endDate)) {
                propagandaMaterialsRecipients1 = propagandaMaterialsRecipientsRepository.findByApplicantAndApplicationDate(user.getId(), startDate, LocalDate.now());
            } else {
                propagandaMaterialsRecipients1 = propagandaMaterialsRecipientsRepository.findByApplicantAndApplicationDate(user.getId(), startDate, endDate);
            }
            result.add(user.getName());
            for (int i = 1; i < heads.size() - 1; i++) {
                int total = 0;
                for (PropagandaMaterialsRecipients propagandaMaterialsRecipients : propagandaMaterialsRecipients1) {
                    for (PropagandaMaterials propagandaMaterials : propagandaMaterialsRecipients.getPropagandaMaterials()) {
                        if (heads.get(i).equals(propagandaMaterials.getName())) {
                            total = total + propagandaMaterials.getQuantity();
                        }
                    }
                }
                result.add(String.valueOf(total));
            }
            //对每一行进行总计
            int total = 0;
            for (int i = 1; i < result.size(); i++) {
                total = total + Integer.parseInt(result.get(i));
            }
            result.add(String.valueOf(total));
            statisticsResult.add(result);
        }

        //对每一列进行总计
        List<String> totalResult = new ArrayList<>();
        totalResult.add("总计");
        for (int i = 1; i < heads.size(); i++) {
            int total = 0;
            for (int j = 0; j < statisticsResult.size(); j++) {
                total = total + Integer.parseInt(statisticsResult.get(j).get(i));
            }
            totalResult.add(String.valueOf(total));
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("heads", heads);
        resultMap.put("contents", statisticsResult);
        resultMap.put("foots", totalResult);

        return resultMap;
    }

    @Override
    public void deletedPropagandaMaterialsRecipients(String id) {
        PropagandaMaterialsRecipients propagandaMaterialsRecipients = getPropagandaMaterialsRecipients(id);
        for (PropagandaMaterials propagandaMaterials : propagandaMaterialsRecipients.getPropagandaMaterials()) {
            propagandaMaterialsService.delete(propagandaMaterials);
        }
        propagandaMaterialsRecipientsRepository.delete(id);
    }

    //宣传物资领用统计报表
    @Override
    public List<Map<String, Integer>> getPropagandaMaterialsRecipientsStatisticsForm(LocalDate startDate, LocalDate endDate) {
        //获取时间段内所有的宣传物资领用申请
        List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients1 = findByApplicationDate(startDate, endDate);
        //获取宣传物资的名称
        Set<String> propagandaMaterialsNames = getPropagandaMaterialsNames(propagandaMaterialsRecipients1);

        //统计所有的宣传物资领用数量
        Map<String, Integer> statisticsTotalData = statisticsData(propagandaMaterialsNames, propagandaMaterialsRecipients1);
        //统计所有的宣传物资实际领用数量
        int approvalStatus = 3;
        List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients2 = propagandaMaterialsRecipientsRepository.findByApplicationDateAndApprovalStatus(startDate, endDate, approvalStatus);
        Map<String, Integer> statisticsPracticalData = statisticsData(propagandaMaterialsNames, propagandaMaterialsRecipients2);
        //将统计数据放到List中
        List<Map<String, Integer>> data = new ArrayList<>();
        data.add(statisticsTotalData);
        data.add(statisticsPracticalData);
        return data;
    }

    //获取宣传物资的名称，返回Set
    private Set<String> getPropagandaMaterialsNames(List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients1) {
        Set<String> propagandaMaterialsNames = new HashSet<>();
        //获取宣传物资名称
        for (PropagandaMaterialsRecipients propagandaMaterialsRecipients : propagandaMaterialsRecipients1) {
            for (PropagandaMaterials propagandaMaterials : propagandaMaterialsRecipients.getPropagandaMaterials()) {
                propagandaMaterialsNames.add(propagandaMaterials.getName());
            }
        }
        return propagandaMaterialsNames;
    }

    //根据宣传物资的名称进行统计
    private Map<String, Integer> statisticsData(Set<String> propagandaMaterialsNames, List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients1) {
        Map<String, Integer> statisticsPracticalData = new LinkedHashMap<>();
        for (String name : propagandaMaterialsNames) {
            int total = 0;
            for (PropagandaMaterialsRecipients propagandaMaterialsRecipients : propagandaMaterialsRecipients1) {
                for (PropagandaMaterials propagandaMaterials : propagandaMaterialsRecipients.getPropagandaMaterials()) {
                    if (name.equals(propagandaMaterials.getName())) {
                        total = total + propagandaMaterials.getQuantity();
                    }
                }
            }
            statisticsPracticalData.put(name, total);
        }
        return statisticsPracticalData;
    }

    //通过日期查询宣传物资领用
    private List<PropagandaMaterialsRecipients> findByApplicationDate(LocalDate startDate, LocalDate endDate) {
        List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients = null;
        if (startDate == null || "".equals(startDate)) {
            propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findAll();
        } else if (endDate == null || "".equals(endDate)) {
            propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicationDate(startDate, LocalDate.now());
        } else {
            propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicationDate(startDate, endDate);
        }
        return propagandaMaterialsRecipients;
    }

}
