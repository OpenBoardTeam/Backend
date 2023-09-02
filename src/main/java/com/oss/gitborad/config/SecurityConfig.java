package com.oss.gitborad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${REDIRECT_URI}")
    private String redirect_uri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login().defaultSuccessUrl(redirect_uri);
        http.
                /*
                TODO: 검토 필요
                작성일: 23.08.25
                문제: CSRF 토큰 방어 탓에 POST, DELETE API에서 403 Forbidden 에러가 발생함.
                내용: CSRF 토큰 방어를 해제함. 추후 해당 부분에 대한 보안 점검이 필요.
                 */
                csrf().disable().
                authorizeRequests().
                antMatchers("/admin").hasAnyRole("ADMIN").
                anyRequest().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(defaultHttpFirewall());
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

}
