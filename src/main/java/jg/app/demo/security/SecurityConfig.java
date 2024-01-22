package jg.app.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
  @Autowired
  private BasicUserDetailsService userDetailsService;
  
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .build();
  }
  
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder());
    
    http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui/index.html")
            .permitAll())
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        .authenticationManager(authenticationManagerBuilder.build())
        .httpBasic(Customizer.withDefaults());
    
    return http.build();
  }
}
