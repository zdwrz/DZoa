package com.dz.oa.listener;

import com.dz.oa.utility.Constants;
import com.dz.oa.vo.UserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by Dawei on 8/31/16.
 */
@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    HttpSession session;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
        UserDetailsVO userDetails = (UserDetailsVO) event.getAuthentication().getPrincipal();
        session.setAttribute(Constants.USER_ID, userDetails.getDetailInfo().getId());
        session.setAttribute(Constants.ENTERPRISE_ID, 1);
    }
}