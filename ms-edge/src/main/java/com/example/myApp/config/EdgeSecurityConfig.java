package com.example.myApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import com.example.myApp.security.EdgeAuthenticationProvider;

import java.util.List;

@Configuration
public class EdgeSecurityConfig {
    @Bean
    AuthenticationManager authenticationManager(EdgeAuthenticationProvider edgeAuthenticationProvider) {
        return new ProviderManager(List.of(edgeAuthenticationProvider));
    }

    @Bean
    EdgeAuthenticationProvider edgeAuthenticationProvider(IntegrationDispatcher integrationDispatcher) {
        return new EdgeAuthenticationProvider(integrationDispatcher);
    }
}
