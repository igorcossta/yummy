package io.igorcossta.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> req.requestMatchers("/css/**",
                                "/js/**",
                                "/img/**",
                                "/",
                                "/login",
                                "/signup",
                                "/recipes",
                                "/recipes/details/**")
                        .permitAll())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                // login form config
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                )
                // logout config
                .logout(logoutConf ->
                        logoutConf.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/"))
                .build();
    }
}
