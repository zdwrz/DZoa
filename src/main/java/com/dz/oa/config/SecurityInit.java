package com.dz.oa.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
    //make sure the multipart filter is before the spring security.
    //in order to make the csrf work.
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, new MultipartFilter());
    }
}
