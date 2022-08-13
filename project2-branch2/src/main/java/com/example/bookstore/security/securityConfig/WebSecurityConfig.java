package com.example.bookstore.security.securityConfig;

import com.example.bookstore.repository.UserRepo;
//import com.example.bookstore.services.implementations.UserDetailsImpl;
import com.example.bookstore.security.MyBasicAuthenticationEntryPoint;
import com.example.bookstore.services.implementations.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo) {
        return new UserDetailsImpl(userRepo);
    }
    @Autowired
    private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf().disable().authorizeRequests()

                .antMatchers(HttpMethod.GET,"/api/v1/authors/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/api/v1/authors/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/api/v1/books/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/api/v1/books/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/api/v1/genres/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/api/v1/genres/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/api/v1/publishers/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/api/v1/publishers/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/api/v1/orders/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/api/v1/orders/**").hasAuthority("ADMIN")

                .antMatchers("/api/v1/users/**").hasAuthority("ADMIN")

                .antMatchers("/api/v1/user/users/saveUser").permitAll()
                .antMatchers("/api/v1/user/**").hasAuthority("USER")


                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().httpBasic().authenticationEntryPoint(myBasicAuthenticationEntryPoint);

        return http.build();


    }


}
