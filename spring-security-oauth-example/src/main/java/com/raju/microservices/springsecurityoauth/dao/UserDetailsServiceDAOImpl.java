package com.raju.microservices.springsecurityoauth.dao;

import com.raju.microservices.springsecurityoauth.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 */
@Repository
public class UserDetailsServiceDAOImpl extends JdbcDaoSupport {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceDAOImpl.class);

    @Autowired
    public UserDetailsServiceDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public User findUserByUserName(String userName) {
        logger.info(":findUserByUserName=> " + userName);
        String getUser = "select username as userName, password as crdDtls, active_ind as activeInd " +
                "from oauth_user_details where username = ?";
        List<User> users = (List<User>) getJdbcTemplate().query(getUser, new Object[] { userName },
                new BeanPropertyRowMapper<User>(User.class));
        User user = users.size() > 0 ? users.get(0) : null;
        logger.info(":findUserByUserName : user=> " + user);
        return user;
    }
}
