package org.svtcc.online.management.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes != null && configAttributes.size() != 0) {
            Iterator<ConfigAttribute> configIterator = configAttributes
                    .iterator();
            while (configIterator.hasNext()) {
                ConfigAttribute configAttribute = configIterator.next();
                String needRole = ((SecurityConfig) configAttribute)
                        .getAttribute();

                for (GrantedAuthority grantedAuthority : authentication
                        .getAuthorities()) {
                    if (needRole.trim().equals(grantedAuthority.getAuthority())) {
                        return;
                    }
                }
            }
        }

        throw new AccessDeniedException("你没有权限去访问该资源！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
