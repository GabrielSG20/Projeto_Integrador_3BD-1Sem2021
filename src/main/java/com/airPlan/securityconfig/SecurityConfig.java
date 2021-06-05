package com.airPlan.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/code-create").hasAnyRole("4","3")
                .antMatchers("/code-consult").hasAnyRole("4","2","3")
                .antMatchers("/code-import").hasAnyRole("4","3")
                .antMatchers("/code-edit").hasAnyRole("4","3")

                .antMatchers("/lep-create").hasRole("3")

                .antMatchers("/pdf-full").hasRole("3")
                .antMatchers("/pdf-delta").hasRole("3")

                .antMatchers("/create-user").hasRole("1")

                .antMatchers("/menu").hasAnyRole("1","2","3","4")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){return NoOpPasswordEncoder.getInstance();}
}
