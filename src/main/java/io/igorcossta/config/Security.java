package io.igorcossta.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> req.requestMatchers(
                                "/", "/login", "/registration/**",
                                "/css/**", "/js/**", "/img/**", "/receitas")
                        .permitAll())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                // login form config
                .formLogin(formLogin ->
                        formLogin.loginPage("/login").defaultSuccessUrl("/")
                )
                // logout config
                .logout(logoutConf ->
                        logoutConf.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/"))
                .build();
    }
}
