package edu.miu.cs489.studyplus.config;

import edu.miu.cs489.studyplus.model.Permission;
import edu.miu.cs489.studyplus.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
      private final AuthenticationProvider authenticationProvider;
      private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.

                csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        request ->
                                request.requestMatchers("/api/v1/auth/*").permitAll()
                                        .requestMatchers("/api/v1/studies/**").permitAll()
                                        .requestMatchers("/").permitAll()
                                        .requestMatchers("/studyplus/**").permitAll()
                                        .requestMatchers("/studies/**").permitAll()
                                        .requestMatchers("/participants/**").permitAll()
                                        .requestMatchers("/api/p1/participants/**").permitAll()
                                        .requestMatchers("/api/v1/coordinator").hasRole(Role.COORDINATOR.name())
                                        .requestMatchers("/api/v1/management/**").hasAnyRole(Role.COORDINATOR.name(), Role.PARTICIPANT.name())
                                        .requestMatchers("/api/v1/management/participant-only").hasAnyAuthority(
                                                Permission.PARTICIPANT_READ.getPermission(),
                                                Permission.PARTICIPANT_WRITE.getPermission())
                                        .anyRequest()
                                        .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
