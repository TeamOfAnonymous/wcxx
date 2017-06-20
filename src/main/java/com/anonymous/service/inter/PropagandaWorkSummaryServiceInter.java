package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaWorkSummary;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Shero on 2017-06-13 0013.
 */
public interface PropagandaWorkSummaryServiceInter {

    PropagandaWorkSummary add(PropagandaWorkSummary propagandaWorkSummary);

    PropagandaWorkSummary getById(String id);

    boolean delete(String[] ids);

    Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(Integer currentPage, Integer size, String title, String draftMan, LocalDate draftDate, Integer approvalStatus);

    boolean file(String[] ids);
}
