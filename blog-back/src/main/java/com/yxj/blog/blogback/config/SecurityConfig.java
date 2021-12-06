//package com.yxj.blogback.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * 这里先不连接数据库了
//     */
//
//    @Autowired
//    LoginSuccessHandler loginSuccessHandler;
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}123456789")
//                .roles("USER");
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        //http.authorizeRequests()
//        //        .antMatchers("/login.jsp", "/failer.jsp", "/css/**", "/img/**",
//        //                "/plugins/**").permitAll()
//        //        .antMatchers("/**").hasAnyRole("USER")
//        //        .anyRequest()
//        //        .authenticated()
//        //        .and()
//        //        .formLogin()
//        //        .loginPage("/login.jsp")
//        //        .loginProcessingUrl("/login")
//        //        .successForwardUrl("/index.jsp")
//        //        .failureForwardUrl("/failer.jsp")
//        //        .permitAll()
//        //        .and()
//        //        .logout()
//        //        .logoutUrl("/logout")
//        //        .invalidateHttpSession(true)
//        //        .logoutSuccessUrl("/login.jsp")
//        //        .permitAll()
//        //        .and()
//        //        .csrf()
//        //        .disable();
//        http.cors().and().csrf().disable().
//                 authorizeRequests()
//                .antMatchers("/css/**", "/img/**","/js/**","fonts/**","/index.html","/avatar/**,/product").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("http://localhost:8080")
//                .loginProcessingUrl("/login")
//                .successHandler(loginSuccessHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
//                .logoutSuccessUrl("/login.jsp")
//                .permitAll();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//        return httpServletRequest -> {
//            CorsConfiguration cfg = new CorsConfiguration();
//            cfg.addAllowedHeader("*");
//            cfg.addAllowedMethod("*");
//            cfg.addAllowedOrigin("*");
//            cfg.setAllowCredentials(true);
//            cfg.checkOrigin("*");
//            return cfg;
//        };
//    }
//}