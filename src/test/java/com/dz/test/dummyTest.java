package com.dz.test;

import org.trs.oa.utility.OaUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by daweizhuang on 8/26/16.
 */
public class dummyTest {
    @Test
    public void testBcrypt(){
       BCryptPasswordEncoder bCEncoder =  new BCryptPasswordEncoder();
        System.out.println(bCEncoder.encode("1"));

        System.out.println(bCEncoder.matches("1", "$2a$10$buNS49tuUVqCzKcAyqxAT.73H/9I44C43B4iJcq4IK0dB3uqodb0G"));
        assertTrue(true);
    }

    @Test
    public void testTsInputKey() {
        String input = "0_0_adf";
        assertTrue(input.matches("^[0-9]+_[0-9]+_[A-Za-z]+$"));
    }

    @Test
    public void testUtilDate(){
        Date dateOfMonday = new Date();
        String weekDay = "sunday";
        Date slotDate = OaUtils.getDateOfWeekDay(dateOfMonday, weekDay);
        System.out.println(dateOfMonday);
        System.out.println(slotDate);
    }
}
