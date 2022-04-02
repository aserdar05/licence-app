package com.serdar.licenceapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
@ComponentScan("com.serdar.licenceapp.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LicenseUserDetailsService licenseUserDetailService;
    @Autowired
    private UserAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Form login provides support username and password being provided through an html form.
        //HTTP basic auth uses an HTTP header in order to provide the username when making a request to the server

        //Permit all requests inside the web application
//        http.authorizeRequests().
//                anyRequest().permitAll().
//                and().formLogin().
//                and().httpBasic();

        //Deny all requests inside the web application
//        http.authorizeRequests().
//                anyRequest().denyAll().
//                and().formLogin().
//                and().httpBasic();

        //By default any requests with HTTPS methods that can update data like POST, PUT
        // will be stopped with 403 error due to CSRF protection.
        // We can disable the same for now and enable it in the coming changes when we started generating CSRF tokens.

        //Spring security offers three types of matchers methods to configure endpoints security.
        //1- MVC matchers
        //2- Ant matchers
        //3- Regex matchers
        http.csrf().disable().//Disables csrf attack configurations
                authorizeRequests().
                mvcMatchers("/home").permitAll().
                mvcMatchers("/user/**").authenticated().
                mvcMatchers("/admin/**").hasRole("ADMIN").
                and().formLogin().
                //loginPage("/login"). Define the login page
                // defaultSuccessUrl("/dashboard")
                //failureUrl("/login?error=true").permitAll()//Define permit url and permit the failure url
                and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).
                and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Choose one of the providers
        //auth.userDetailsService(licenseUserDetailService).passwordEncoder(getPasswordEncoder());
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
