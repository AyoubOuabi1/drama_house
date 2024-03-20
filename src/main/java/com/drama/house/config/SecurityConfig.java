package com.drama.house.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/movies/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/genres/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/persons/**")
                                .permitAll()

                                .requestMatchers(HttpMethod.POST,"/api/V1/movies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/V1/movies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/V1/movies/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST,"/api/V1/genres/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/V1/genres/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/V1/genres/**").hasRole("ADMIN")


                                .requestMatchers(HttpMethod.POST,"/api/V1/persons/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/V1/persons/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/V1/persons/**").hasRole("ADMIN")


                                .requestMatchers("/api/v1/watch-list/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/api/v1/users/**").hasAnyRole("USER","ADMIN")
                                .anyRequest()

                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        ;

        return http.build();
    }

}
