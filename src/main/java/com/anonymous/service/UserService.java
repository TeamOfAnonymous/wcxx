package com.anonymous.service;

import com.anonymous.domain.organization.Organization;
import com.anonymous.domain.User;
import com.anonymous.repository.UserRepository;
import com.anonymous.service.inter.OrganizationServiceInter;
import com.anonymous.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public Page<User> getUsersForPage(Integer currentPage, Integer size, String name, String sex, String organization, String post) {
        Pageable pageable = new PageRequest(currentPage, size);
        return userRepository.findByNameAndSexAndOrganizationAndPost(name, sex, organization, post,1, pageable);
    }

    @Override
    public boolean deletedUser(String[] ids) {
        for (String id : ids) {
            User user = userRepository.findOne(id);
            user.setStatus(0);
            userRepository.save(user);
        }
        return true;
    }

    /**
     * 用于用户登陆
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        System.out.println( user );

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new SimpleGrantedAuthority("admin") );
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        }
    }


    /**
     * 获取当前登陆的用户
     * @return
     */
    public User getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        if( userDetails == null ){
            throw new RuntimeException("获取当前登陆用户 出现异常");
        }
        User user = userRepository.findByUsername(userDetails.getUsername()) ;
        return user;
    }
}
