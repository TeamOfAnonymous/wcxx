package com.anonymous.service.inter;

import com.anonymous.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
public interface UserServiceInter {
    User add(User user);

    List<User> findByOrganization(String organization_id);

    User edit(User user);

    User getUser(String id);

    Page<User> getUsersForPage(Integer currentPage, Integer size, String name, String sex, String organization, String post);

    boolean deletedUser(String[] ids);
}
