package com.raju.microservices.springsecurityoauth.controller;

import com.raju.microservices.springsecurityoauth.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class OAuthSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(OAuthSecurityController.class);

    /**
     *
     * @return
     */
    @GetMapping(value = "/api/admin/security")
    public ResponseEntity<User> adminSecurity() {
        logger.info("OAuthSecurityController:adminSecurity method called!");
        User user = new User();
        user.setUserName("ADMIN");
        user.setActiveInd("Y");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/api/user/security")
    public ResponseEntity<User> userSecurity() {
        logger.info("OAuthSecurityController:userSecurity method called!");
        User user = new User();
        user.setUserName("USER");
        user.setActiveInd("Y");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
