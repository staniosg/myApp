package com.example.myApp.security;

import com.example.myApp.messaging.MessagingDispatcher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class EdgeAuthenticationProvider implements AuthenticationProvider {

    private final MessagingDispatcher messagingDispatcher;

    public EdgeAuthenticationProvider(MessagingDispatcher messagingDispatcher) {
        this.messagingDispatcher = messagingDispatcher;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
