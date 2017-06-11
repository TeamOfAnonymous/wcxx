package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterials;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<List<String>> getPropagandaMaterialsRecipientsByApplicationDate(LocalDate startDate, LocalDate endDate) {
        List<PropagandaMaterialsRecipients> propagandaMaterialsRecipients = new ArrayList<>();
        if (startDate == null || "".equals(startDate)) {
            propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findAll();
        } else if (endDate == null || "".equals(endDate)) {
            propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicationDate(startDate, LocalDate.now());
        } else {
            propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicationDate(startDate, endDate);
        }

        Set<User> users = new HashSet<>();
        for (PropagandaMaterialsRecipients propagandaMaterialsRecipients1 : propagandaMaterialsRecipients) {
            users.add(propagandaMaterialsRecipients1.getApplicant());
        }
        List<List<String>> statisticsResult = createHead();

        for (User user : users) {
            List<String> result = initResult(user.getName());
            if (startDate == null || "".equals(startDate)) {
                propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicant(user.getId());
            } else if (endDate == null || "".equals(endDate)) {
                propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicantAndApplicationDate(user.getId(), startDate, LocalDate.now());
            } else {
                propagandaMaterialsRecipients = propagandaMaterialsRecipientsRepository.findByApplicantAndApplicationDate(user.getId(), startDate, endDate);
            }
            for (PropagandaMaterialsRecipients propagandaMaterialsRecipients1 : propagandaMaterialsRecipients) {
                for (PropagandaMaterials propagandaMaterials : propagandaMaterialsRecipients1.getPropagandaMaterials()) {
                    if ("宣传手册".equals(propagandaMaterials.getName())) {
                        result.set(1, String.valueOf(propagandaMaterials.getQuantity() + Integer.parseInt(result.get(1))));
                    } else if ("纪念胸章".equals(propagandaMaterials.getName())) {
                        result.set(2, String.valueOf(propagandaMaterials.getQuantity() + Integer.parseInt(result.get(2))));
                    } else if ("样品吊坠".equals(propagandaMaterials.getName())) {
                        result.set(3, String.valueOf(propagandaMaterials.getQuantity() + Integer.parseInt(result.get(3))));
                    } else if ("电影票".equals(propagandaMaterials.getName())) {
                        result.set(4, String.valueOf(propagandaMaterials.getQuantity() + Integer.parseInt(result.get(4))));
                    } else {
                        result.set(5, String.valueOf(propagandaMaterials.getQuantity() + Integer.parseInt(result.get(5))));
                    }
                }
            }
            result.set(6, String.valueOf(Integer.parseInt(result.get(1)) + Integer.parseInt(result.get(2)) + Integer.parseInt(result.get(3)) + Integer.parseInt(result.get(4)) + Integer.parseInt(result.get(5))));
            statisticsResult.add(result);
        }
        List<String> totalResult = initResult("总计");
        for (int i = 1; i < totalResult.size(); i++) {
            for (int j = 1; j < statisticsResult.size(); j++) {
                totalResult.set(i, String.valueOf(Integer.parseInt(totalResult.get(i)) + Integer.parseInt(statisticsResult.get(j).get(i))));
            }
        }
        statisticsResult.add(totalResult);

        return statisticsResult;
    }

    @Override
    public void deletedPropagandaMaterialsRecipients(String id) {
        PropagandaMaterialsRecipients propagandaMaterialsRecipients = getPropagandaMaterialsRecipients(id);
        for (PropagandaMaterials propagandaMaterials : propagandaMaterialsRecipients.getPropagandaMaterials()) {
            propagandaMaterialsService.delete(propagandaMaterials);
        }
        propagandaMaterialsRecipientsRepository.delete(id);
    }

    private List<String> initResult(String name) {
        List<String> result = new ArrayList<>();
        result.add(0, name);
        result.add(1, String.valueOf(0));
        result.add(2, String.valueOf(0));
        result.add(3, String.valueOf(0));
        result.add(4, String.valueOf(0));
        result.add(5, String.valueOf(0));
        result.add(6, String.valueOf(0));
        return result;
    }

    private List<List<String>> createHead() {
        List<List<String>> statisticsResult = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("申请人");
        head.add("宣传手册");
        head.add("纪念胸章");
        head.add("样品吊坠");
        head.add("电影票");
        head.add("其他");
        head.add("总计");
        statisticsResult.add(head);
        return statisticsResult;
    }

}
