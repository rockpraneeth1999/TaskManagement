package com.example.TaskManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/user/**","/task/**")
//                .hasRole("ADMIN")
//                .requestMatchers("/user/add**","/user/delete**","/user/update**")
//                .hasRole("USER")
//                .requestMatchers("/task/add**","/task/get-tasks/**","/task/update**","/task/delete/**","/sort/{field}/{email}/{ascending}"
//                ,"/pagination/{email}/{offset}/{pageSize}","/pagination-sorting/{email}/{offset}/{field}/{pageSize}/{ascending}"
//                ,"/task-by-date/{email}/{date}","/task-by-status/{email}/{status}")
//                .hasRole("USER")
//                .requestMatchers("/user/add")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin();

        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
