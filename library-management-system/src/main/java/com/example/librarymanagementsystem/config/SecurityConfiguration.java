package com.example.librarymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/edit/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.PUBLISHER.name())
                        .requestMatchers("/delete/**").hasRole(UserRole.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("user_admin")
                .password("{noop}1234")
                .roles(UserRole.ADMIN.name())
                .build();
        UserDetails publisher = User.builder()
                .username("user_publisher")
                .password("{noop}123")
                .roles(UserRole.PUBLISHER.name())
                .build();
        UserDetails readonly = User.builder()
                .username("user_read_only")
                .password("{noop}12")
                .roles(UserRole.READ_ONLY.name())
                .build();
        return new InMemoryUserDetailsManager(admin, publisher, readonly);
    }


}
