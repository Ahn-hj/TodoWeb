package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // @Configuration 어노테이션 추가
@EnableWebSecurity
@Slf4j
//WebSecurityConfigurerAdapter 상속 제거 ==> Spring Security 5.7.0부터 deprecated
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //configure 메소드 -> SecurityFilterChain Bean 변경
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	//*람다식 사용 메소드 체이닝
	        .cors(cors -> cors.configure(http))
	        .csrf(csrf -> csrf.disable())
	        .httpBasic(basic -> basic.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	        		//antMatchers() -> requestMatchers() Spring Security 6.0부터 통합
	                .requestMatchers("/", "/auth/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            //addFilterAfter()->addFilterBefore() 변경
	        	//CorsFilter->UsernamePasswordAuthenticationFilter 변경
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        //SecurityFilterChain 반환
	        return http.build();
	    }

    
}
