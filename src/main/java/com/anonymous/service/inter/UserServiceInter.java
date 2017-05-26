package com.anonymous.service.inter;

import com.anonymous.domain.User;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
public interface UserServiceInter {
    User add(User user);

    List<User> findByOrganization(String organization_id);

    User edit(User user);

    User getUser(String id);
}
