package com.dz.oa.service;

import com.dz.oa.dao.UserDAO;
import com.dz.oa.vo.UserDetailsVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by daweizhuang on 8/11/16.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    public static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDetailsVO userVO = userDAO.findVOByUsername(username);
        if (userVO == null)
            throw new UsernameNotFoundException("username " + username
                    + " not found");
       //LOGGER.debug(".........." + userVO);
        return userVO;
    }

}