/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.user;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author webdesign
 */
@Service
public class UserService implements UserDetailsService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDAL userDAL;

    @Autowired
    private RoleHierarchy roleHierarchy;

    private List<User> users;

    public List<User> findByRole(Role role) {
        List<User> found = new ArrayList<>();

        for (User u : users) {
            if (u.getRole().equals(role)) {
                found.add(u);
            }
        }
        return found;
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        System.out.println("Any Role in Login??");
        System.out.println("What is String :" + string);
        try {
            System.out.println("Inside Try??");
            User user = userDAL.findByUsername(string);
            logger.debug("Get this object :{}", user);
            System.out.println("What is the role of an user :"+user.getRole().name());
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole().name());

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(sga);

            UserDetails ud = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    roleHierarchy.getReachableGrantedAuthorities(authorities));
            return ud;
        } catch (EmptyResultDataAccessException se) {
            System.out.println("Getting ANy Exception??");
            se.printStackTrace();
            throw new UsernameNotFoundException("SQL Error", se);
        }
    }
    
}
