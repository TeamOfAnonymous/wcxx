package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.utils.StatisticsUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 */
public interface PropagandaMaterialsRecipientsServiceInter {
    PropagandaMaterialsRecipients add(PropagandaMaterialsRecipients propagandaMaterialsRecipients);

    PropagandaMaterialsRecipients getPropagandaMaterialsRecipients(String id);

    List<StatisticsUtil> getPropagandaMaterialsRecipientsByApplicationDate(LocalDate startDate, LocalDate endDate);
}
