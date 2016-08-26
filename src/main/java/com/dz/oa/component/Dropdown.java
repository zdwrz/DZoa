package com.dz.oa.component;

import com.dz.oa.entity.AdminLookup;
import com.dz.oa.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Dawei on 8/24/16.
 */
@Component
public class Dropdown {

    @Autowired
    LookupService ls;

    public static Map<Integer, AdminLookup> adminLookupMap;
    public static List<AdminLookup> notificationTypeList;

    @PostConstruct
    public void init() {
        List<AdminLookup> lkupList = ls.getAllLookup();
        adminLookupMap = lkupList.stream().collect(Collectors.toMap(AdminLookup::getId, Function.identity()));
    }

    public List<AdminLookup> getNotificationTypeList(){
        if(notificationTypeList == null){
            notificationTypeList = getAdminLookupByType("NOTIFICATION_TYPE");
        }
        return notificationTypeList;
    }

    private List<AdminLookup> getAdminLookupByType(final String notificationType) {
        List<AdminLookup> resList = adminLookupMap.values().stream().filter(al -> notificationType.equals(al.getType())).collect(Collectors.toList());
        return resList;
    }


}
