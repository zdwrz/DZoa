package com.dz.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by daweizhuang on 8/26/16.
 */
public class dummy {
    @Test
    public void testBcrypt(){
       BCryptPasswordEncoder bCEncoder =  new BCryptPasswordEncoder();
        System.out.println(bCEncoder.encode("1"));
        System.out.println(bCEncoder.encode("1"));
        System.out.println(bCEncoder.matches("1", "$2a$10$buNS49tuUVqCzKcAyqxAT.73H/9I44C43B4iJcq4IK0dB3uqodb0G"));
    }
}
