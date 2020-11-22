package com.bruno.ws.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Como essa classe disponibiliza segurança, deverá herdar WebSecurityConfigurerAdapter para adaptar a autenticação
@Configuration @EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean // O spring faz escanear essa classe @Configuration com essa anotação
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // Esse método irá criptografar as senhas de usuários
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override // método da classe pai
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManagerBean();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Override // método da classe
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}
