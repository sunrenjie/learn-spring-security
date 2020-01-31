package com.baeldung.lss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class LssSecurityConfig extends WebSecurityConfigurerAdapter {

    public LssSecurityConfig() {
        super();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
                .antMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/auth/login").permitAll().and()
                .logout().logoutUrl("/auth/logout")
        ;
    } // @formatter:on

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off 
        auth.
            inMemoryAuthentication().passwordEncoder(passwordEncoder()).
            withUser("user").password(passwordEncoder().encode("pass")).
            roles("USER");
    } // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
