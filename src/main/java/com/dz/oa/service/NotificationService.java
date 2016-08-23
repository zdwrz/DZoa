package com.dz.oa.service;

import com.dz.oa.entity.Notification;

import java.util.List;

/**
 * Created by daweizhuang on 8/23/16.
 */
public interface NotificationService {
    List<Notification> getAllNotification();
    List<Notification> getNotiForToday();
}
