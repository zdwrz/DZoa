package com.dz.oa.dao;

import com.dz.oa.entity.Notification;

import java.util.List;

/**
 * Created by daweizhuang on 8/23/16.
 */
public interface NotificationDAO {
    Notification save(Notification note);
    List<Notification> findNotificationOfToday();

    List<Notification> findALl();

    boolean inactiveNoti(Integer notificationId);
}
