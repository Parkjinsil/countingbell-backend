package com.kh.countingBell.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests();

        http.cors()
                .and()
                .csrf().disable() //쓰지 않아서 disable로 막아놓은거
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
//                .requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll() //hasAnyRole관리자(여러명에), hasRole한명에관한거 //api로 요청은 허용
//                .anyRequest().authenticated(); // 나머지는 요청에는 권한이 필요하다
        .anyRequest().permitAll(); //모든요청을 허용 //token안쓸때 위에 두개주석 말고 이걸로 해주기

        // JWT 토큰 생성부터 필터처리까지 전부 세팅
        // JWT 필터 등록
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);


        return http.build();
    }
}
