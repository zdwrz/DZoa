package com.dz.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by daweizhuang on 9/1/16.
 */
@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String msgName, String[] param) {
        return messageSource.getMessage(msgName, param, LocaleContextHolder.getLocale());
    }
}