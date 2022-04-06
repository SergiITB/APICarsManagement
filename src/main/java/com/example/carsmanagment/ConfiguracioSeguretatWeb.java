package com.example.carsmanagment;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ConfiguracioSeguretatWeb extends WebSecurityConfigurerAdapter {
//    private final AuthenticationEntryPoint entryPoint;
//    private final ElMeuUserDetailsService userDetailsService;
//    private final PasswordEncoder xifrat;

//Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().anyRequest();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(xifrat);
//    }

}
