package com.example.bookstore.security.securityConfig;

import com.example.bookstore.Repos.UserRepo;
//import com.example.bookstore.services.implementations.UserDetailsImpl;
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
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig   {
    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo){
        return new UserDetailsImpl(userRepo);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                /*for authors */
//                .antMatchers("/api/v1/authors/updateAuthor").hasAuthority("ADMIN")
//                .antMatchers("/api/v1/authors/deleteAuthor/{authorID}").hasAuthority("ADMIN")
//                .antMatchers("/api/v1/authors/saveAuthor").permitAll()
//                .antMatchers("/employee/fetch/*").hasAnyAuthority("EMPLOYEE","ADMIN")
                .antMatchers("/api/v1/authors/*").permitAll()
                /*for books */
                .antMatchers("/api/v1/books/deleteBook/{bookID}").hasAuthority("ADMIN")
                .antMatchers("/api/v1/books/saveBook").hasAuthority("ADMIN")
                .antMatchers("/api/v1/books/updateBook").hasAuthority("ADMIN")
                .antMatchers("/api/v1/books/*").permitAll()
                /*for genres */
                .antMatchers("/api/v1/genres/deleteGenre/{genreID}").hasAuthority("ADMIN")
                .antMatchers("/api/v1/genres/saveGenre").hasAuthority("ADMIN")
                .antMatchers("/api/v1/genres/updateGenre").hasAuthority("ADMIN")
                .antMatchers("/api/v1/genres/*").permitAll()
                /*for orders */
                .antMatchers("/api/v1/orders/ordersList").hasAuthority("ADMIN")
                .antMatchers("/api/v1/genres/saveGenre").hasAuthority("ADMIN")
                .antMatchers("/api/v1/genres/updateGenre").hasAuthority("ADMIN")
                .antMatchers("/api/v1/genres/*").permitAll()
                .antMatchers("/login").permitAll()


                .and().formLogin();

//        return http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/orders/order").authenticated()
//                .antMatchers( "/orders/order/**").hasAuthority("ADMIN")
//                .and().httpBasic(Customizer.withDefaults()).build();





        return http.build();




    }




}
