package com.dz.oa.dao;

import com.dz.oa.vo.UserDetailsVO;

/**
 * Created by daweizhuang on 8/11/16.
 */
public interface UserDAO {
    UserDetailsVO findVOByUsername(String username);
}
