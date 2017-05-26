package com.anonymous.service;

import com.anonymous.domain.Organization;
import com.anonymous.domain.User;
import com.anonymous.repository.UserRepository;
import com.anonymous.service.inter.OrganizationServiceInter;
import com.anonymous.service.inter.UserServiceInter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Service
public class UserService implements UserServiceInter {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationServiceInter organizationService;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findByOrganization(String organization_id) {
        Organization organization = organizationService.findById(organization_id);
        return userRepository.findByOrganization(organization);
    }

    @Override
    public User edit(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String id) {
        return userRepository.findOne(id);
    }
}
