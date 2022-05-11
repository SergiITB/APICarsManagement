package com.example.carsmanagment.seguretat;

import com.example.carsmanagment.model.serveis.ElMeuUserDetailsService;
import com.example.carsmanagment.seguretat.jwt.ElMeuAuthenticationEntryPoint;
import com.example.carsmanagment.seguretat.jwt.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracioSeguretatWeb extends WebSecurityConfigurerAdapter {

    private final ElMeuAuthenticationEntryPoint elmeuEntryPoint;
    private final ElMeuUserDetailsService elmeuUserDetailsService;
    private final PasswordEncoder xifrat;

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

//Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().anyRequest();
//    }



//codi per fer una prova autenticant en memòria "inMemoryAuthentication()"
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(xifrat)
//                .withUser("Montse")
//                .password(xifrat.encode("secret"))
//                .roles("ADMIN"); // és necessari posar tots els camps, fins el rol (authorities)
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(elmeuUserDetailsService).passwordEncoder(xifrat);
    }

    @Bean //s'utilitza al Controller del Login d'Usuaris
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .cors()
//                .and()
                .httpBasic()
                .and()
                //    .exceptionHandling().authenticationEntryPoint(elmeuEntryPoint)
                //    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //per poder accedir al h2-console
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()


                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                // .antMatchers(HttpMethod.GET,"/login").permitAll()
                //.antMatchers(HttpMethod.GET, "/me/**").hasRole("ADMIN") //per fer proves del forbidden
                .antMatchers(HttpMethod.GET, "/login/**","/usuaris/**", "/cotxes/**").hasRole("USER")
//                    .antMatchers(HttpMethod.POST, "/usuaris/**", "/videojocs/**").hasRole("USER")
//                    .antMatchers(HttpMethod.PUT, "/videojocs/**").hasRole("USER")
//                    .antMatchers(HttpMethod.DELETE, "/videojocs/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.POST, "/videojocs/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated().and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}