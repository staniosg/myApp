package com.example.myApp.config;

import com.example.myApp.messaging.MessagingDispatcher;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.savedrequest.NullRequestCache;
import com.example.myApp.security.EdgeAuthenticationProvider;

@Configuration
public class WebSecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Register how to handle unauthorized access
        http.setSharedObject(AuthenticationEntryPoint.class,
                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/",
                                "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("edge/login")
                        .permitAll()
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Login failed: " + exception.getMessage() + "\"}");
                        })
                        .successForwardUrl("/edge/user")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_ACCEPTED);
                            response.getWriter().write("Logout accepted");
                        })
                        .invalidateHttpSession(true)
                )
                // avoid unnecessary redirect
                .requestCache(requestCache -> requestCache
                        .requestCache(new NullRequestCache())
                )
                //.httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    AuthenticationProvider authenticationProvider(MessagingDispatcher messagingDispatcher) {
        return new EdgeAuthenticationProvider(messagingDispatcher);
    }
}
