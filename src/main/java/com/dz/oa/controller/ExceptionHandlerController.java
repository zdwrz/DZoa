package com.dz.oa.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Dawei on 8/26/16.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    public static final String DEFAULT_ERROR_VIEW = "error";
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlerController.class);
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        LOGGER.error("Got this exception : " + e, e);
        return DEFAULT_ERROR_VIEW;
    }
}