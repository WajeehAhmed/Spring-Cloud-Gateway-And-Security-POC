package com.cipherLab.user.security;

import com.cipherLab.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.core.env.Environment;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment environment;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
////         Configure AuthenticationManagerBuilder
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        authenticationManagerBuilder.userDetailsService(userService)
//                .passwordEncoder(bCryptPasswordEncoder);
//
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//        AuthenticationFilter authenticationFilter =
//                new AuthenticationFilter(userService, environment, authenticationManager);
//        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
//        http.authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(new AntPathRequestMatcher("/users", "POST")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/users/{id}", "GET")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll())
//                .addFilter(authenticationFilter)
//                .authenticationManager(authenticationManager)
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
//        http.csrf((csrf) -> csrf.disable());
//        return http.build();
//
//    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // Configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        // Create AuthenticationFilter
        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(userService, environment, authenticationManager);
//        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));

        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/users/{id}", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll())
                .addFilter(authenticationFilter)
                .authenticationManager(authenticationManager)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
        return http.build();

    }

}
