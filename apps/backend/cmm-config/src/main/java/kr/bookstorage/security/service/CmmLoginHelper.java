package kr.bookstorage.security.service;

import kr.bookstorage.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * Created by 권성봉 on 8/10/16.
 */
@Slf4j
@Component
public class CmmLoginHelper {

    private static Environment environment;

    @Autowired
    public CmmLoginHelper(Environment environment) {
        CmmLoginHelper.environment = environment;
    }

    public static User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (ObjectUtils.isEmpty(authentication) ||
                authentication.getAuthorities().size() == 0 ||
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
            return null;
        } else {
            CmmUserDetails cud = (CmmUserDetails) authentication.getPrincipal();
            User user = ((User) cud.getUser());
            return user;
        }
    }

    public  static void setUser(User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CmmUserDetails cud = (CmmUserDetails) authentication.getPrincipal();
        cud.setUser(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(cud, user.getUniqueId(), cud.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
