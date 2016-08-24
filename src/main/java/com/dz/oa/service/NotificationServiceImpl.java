package com.dz.oa.service;

import com.dz.oa.dao.NotificationDAO;
import com.dz.oa.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by daweizhuang on 8/23/16.
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationDAO notiDAO;

    @Override
    public List<Notification> getAllNotification() {
        return notiDAO.findALl();
    }

    @Override
    public List<Notification> getNotiForToday() {
        return notiDAO.findNotificationOfToday();
    }

    @Override
    public boolean removeNotification(Integer notificationId) {
        return notiDAO.inactiveNoti(notificationId);
    }
}
