package com.example.bookstore.security.securityConfig;

import com.example.bookstore.Repos.UserRepo;
//import com.example.bookstore.services.implementations.UserDetailsImpl;
import com.example.bookstore.entities.Role;
import com.example.bookstore.services.implementations.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo) {
        return new UserDetailsImpl(userRepo);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/admin/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/v1/user/**").hasAuthority(Role.USER.name())
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated().and().formLogin().and().httpBasic();

        return http.build();


    }


}
