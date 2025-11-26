package nl.mitwnn.ch17.ctrl_z.receptopzak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Sabien Ruijgrok, @Pelle Meuzelaar
 * Configure the security for the recipe application
 */

@Configuration
@EnableWebSecurity
public class RecipeSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "recipe/all", "recipe/detail/*").permitAll()
                        .requestMatchers("/css/**", "/webjars/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/image/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/recipe/all", true)
                        .permitAll()
                )

                .logout((logout) -> logout
                        .logoutSuccessUrl("/"));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
