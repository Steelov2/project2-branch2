package com.example.bookstore.security.securityConfig;

import com.example.bookstore.Repos.UserRepo;
//import com.example.bookstore.services.implementations.UserDetailsImpl;
import com.example.bookstore.entities.Role;
import com.example.bookstore.services.implementations.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/v1/admin/**").permitAll()
                .antMatchers("/api/v1/authors/**").hasAuthority("ADMIN")

                .antMatchers("/api/v1/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/books/**").permitAll()

                .antMatchers("/api/v1/genres/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/genres/**").permitAll()

                .antMatchers("/api/v1/publishers/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/publishers/**").permitAll()

                .antMatchers("/api/v1/orders/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/orders/**").permitAll()

                .antMatchers("/api/v1/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/users/**").permitAll()


                .antMatchers("/api/v1/user/**").hasAuthority("USER")
                .antMatchers("/api/v1/user/users/crateUser").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().httpBasic();

        return http.build();


    }


}
