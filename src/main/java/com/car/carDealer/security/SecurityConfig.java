package com.car.carDealer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password" +
                        "from users where email = ?")
                .authoritiesByUsernameQuery("select email,role" +
                        "from users where email =?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cars/list").hasRole("ADMIN")
                .antMatchers("cars/add").hasRole("ADMIN")
                .antMatchers("/","/**").permitAll()
                .and()
                .formLogin();
    }
}
