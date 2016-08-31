package com.dz.oa.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { PersistenceJPAConfig.class, AppSecurityConfig.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MvcConfig.class};
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
}
