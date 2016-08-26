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

    }
}
