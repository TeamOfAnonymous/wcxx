package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaMaterialsRecipients;

/**
 * Created by WangZK on 2017/5/26.
 */
public interface PropagandaMaterialsRecipientsServiceInter {
    PropagandaMaterialsRecipients add(PropagandaMaterialsRecipients propagandaMaterialsRecipients);

    PropagandaMaterialsRecipients getPropagandaMaterialsRecipients(String id);
}
