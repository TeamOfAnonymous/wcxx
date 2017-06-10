package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaMaterialsRecipients;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 */
public interface PropagandaMaterialsRecipientsServiceInter {
    PropagandaMaterialsRecipients add(PropagandaMaterialsRecipients propagandaMaterialsRecipients);

    PropagandaMaterialsRecipients getPropagandaMaterialsRecipients(String id);

    Page<PropagandaMaterialsRecipients> getPropagandaMaterialsRecipientsForPage(Integer currentPage, Integer size, String title, String department, String applicant, Integer approvalStatus);

    List<List<String>> getPropagandaMaterialsRecipientsByApplicationDate(LocalDate startDate, LocalDate endDate);

    void deletedPropagandaMaterialsRecipients(String id);
}
