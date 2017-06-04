package kr.bookstorage.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

/**
 * Created by 권성봉 on 8/10/16.
 */
public class CmmUserDetails extends org.springframework.security.core.userdetails.User implements SocialUserDetails {

    public CmmUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CmmUserDetails(String username, String password, boolean enabled,
                          boolean accountNotExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNotExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public CmmUserDetails(String username, String password, boolean enabled,
                          boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities, Object userEntity) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setUser(userEntity);
    }

    private Object user;

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getUserId() {
        return ((kr.bookstorage.domain.User) getUser()).getUniqueId().toString();
    }

}
