package githubProjectRepo.dipayan-code97.flightBookingService.security;

import githubProjectRepo.dipayan-code97.flightBookingService.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : Dipayan Paul
 * @created : 8/19/2023, Saturday
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final UserService USER_SERVICE;
    public WebSecurityConfiguration(UserService userService) {
        this.USER_SERVICE = userService;
    }

    public UserService getUSER_SERVICE() {
        return this.USER_SERVICE;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        (httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationRequest -> authorizationRequest
                .anyRequest()
                .permitAll()));
        return (httpSecurity.build());
    }

    protected void authenticationProvider(AuthenticationManagerBuilder managerBuilder) {
        managerBuilder.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(USER_SERVICE);
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        WebSecurityConfiguration that = (WebSecurityConfiguration) objectRef;
        return (Objects.equals(getUSER_SERVICE(), that.getUSER_SERVICE()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getUSER_SERVICE()));
    }

    @Override
    public String toString() {
        return ("WebSecurityConfiguration{" +
                "UserService=" + USER_SERVICE +
                '}');
    }
}
