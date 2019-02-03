package com.raju.microservices.springsecurityoauth.service;

import com.raju.microservices.springsecurityoauth.dao.UserDetailsServiceDAOImpl;
import com.raju.microservices.springsecurityoauth.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */

@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDetailsServiceDAOImpl userDetailsServiceDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(":loadUserByUsername:Begins");
        User userFromDB = userDetailsServiceDAO.findUserByUserName(username);

        if (userFromDB == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        } else if (userFromDB.getActiveInd().equalsIgnoreCase("N")) {
            throw new UsernameNotFoundException("User " + username + " is not activated");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthorityAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
        GrantedAuthority grantedAuthorityUser = new SimpleGrantedAuthority("ROLE_USER");
        if (username.equalsIgnoreCase("ADMIN")) {
            grantedAuthorities.add(grantedAuthorityAdmin);
        } else if (username.equalsIgnoreCase("USER")) {
            grantedAuthorities.add(grantedAuthorityUser);
        }

        return new org.springframework.security.core.userdetails.User(userFromDB.getUserName(),
                String.valueOf(userFromDB.getCrdDtls()), grantedAuthorities);
    }
}
