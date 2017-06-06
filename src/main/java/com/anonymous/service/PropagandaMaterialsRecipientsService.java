package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterials;
import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.domain.User;
import com.anonymous.repository.PropagandaMaterialsRecipientsRepository;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import com.anonymous.service.inter.PropagandaMaterialsServiceInter;
import com.anonymous.utils.StatisticsUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<StatisticsUtil> getPropagandaMaterialsRecipientsByApplicationDate(LocalDate startDate, LocalDate endDate) {
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
        List<StatisticsUtil> statisticsUtils = new ArrayList<>();
        for (User user : users) {
            StatisticsUtil statisticsUtil = new StatisticsUtil();
            statisticsUtil.setUser(user);
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
                        statisticsUtil.setBrochureNum(propagandaMaterials.getQuantity() + statisticsUtil.getBrochureNum());
                    } else if ("纪念胸章".equals(propagandaMaterials.getName())) {
                        statisticsUtil.setBadgeNum(propagandaMaterials.getQuantity() + statisticsUtil.getBadgeNum());
                    } else if ("样品吊坠".equals(propagandaMaterials.getName())) {
                        statisticsUtil.setPendantNum(propagandaMaterials.getQuantity() + statisticsUtil.getPendantNum());
                    } else if ("电影票".equals(propagandaMaterials.getName())) {
                        statisticsUtil.setCinemaTicketNum(propagandaMaterials.getQuantity() + statisticsUtil.getCinemaTicketNum());
                    } else {
                        statisticsUtil.setOther(propagandaMaterials.getQuantity() + statisticsUtil.getOther());
                    }
                }
            }
            statisticsUtils.add(statisticsUtil);
        }

        return statisticsUtils;
    }


}
