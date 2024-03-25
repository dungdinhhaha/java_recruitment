package unigap.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import unigap.api.config.filter.JwtTokenFilter;
import unigap.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SpringSecurityConfig(JwtTokenFilter jwtTokenFilter, CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
//                .cors(cfg -> cfg.disable())
                .csrf(cfg -> cfg.disable())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("users/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/employer/create").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "api/job/create").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "api/resume/create").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "api/employer/update").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "api/job/update").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "api/resume/update").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "api/employer/delete").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "api/job/delete").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "api/resume/delete").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

