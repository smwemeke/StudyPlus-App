package edu.miu.cs489.studyplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.

                csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        request ->
                                request.requestMatchers("/api/v1/auth/*").permitAll()
                                        .anyRequest()
                                        .authenticated()
                );
        return http.build();
    }


}
