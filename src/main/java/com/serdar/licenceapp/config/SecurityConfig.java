package com.serdar.licenceapp.config;

import com.serdar.licenceapp.security.UserAuthenticationProvider;
import com.serdar.licenceapp.security.jwt.JWTAuthorizationFilter;
import com.serdar.licenceapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity//Spring boot application does not require this annotation
@Configuration
@ComponentScan("com.serdar.licenceapp.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private LicenseUserDetailsService licenseUserDetailService;
    @Autowired
    private UserAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
                mvcMatchers("/api/home/**").permitAll().
                mvcMatchers("/api/user/**").hasRole("USER").
                mvcMatchers("/api/admin/**").hasRole("ADMIN").
                and().formLogin().
                //loginPage("/login"). Define the login page
                // defaultSuccessUrl("/dashboard")
                //failureUrl("/login?error=true").permitAll()//Define permit url and permit the failure url
                        and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).
                and().httpBasic();
        //jwt filter
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtTokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Choose one of the providers
        //auth.userDetailsService(licenseUserDetailService).passwordEncoder(getPasswordEncoder());
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Cross origin resource sharing.
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
