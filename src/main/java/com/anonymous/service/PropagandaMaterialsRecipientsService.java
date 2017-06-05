package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterials;
import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.repository.PropagandaMaterialsRecipientsRepository;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import com.anonymous.service.inter.PropagandaMaterialsServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
