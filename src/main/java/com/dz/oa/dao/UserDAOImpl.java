package com.dz.oa.dao;

import com.dz.oa.entity.UserDetail;
import com.dz.oa.vo.UserDetailsVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daweizhuang on 8/11/16.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public UserDetailsVO findVOByUsername(String username) {
        UserDetail detailInfo = new UserDetail();
        detailInfo.setFirstName("Dawei");
        detailInfo.setLastName("Zhuang");
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(grantedAuthority);
        return new UserDetailsVO("a","1",detailInfo,authorities);
    }
}
