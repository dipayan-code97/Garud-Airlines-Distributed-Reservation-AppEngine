package githubProjectRepo.dipayan-code97.bankingService.security;

import githubProjectRepo.dipayan-code97.bankingService.service.BankAccountService;
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
 * @author : Dipayan_Paul
 * @created : 7/28/2023, Friday
 **/
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final BankAccountService BANK_ACCOUNT_SERVICE;
    private final BCryptPasswordEncoder BCRYPT_PASSWORD_ENCODER;

    public WebSecurityConfig(BankAccountService bankAccountService,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.BANK_ACCOUNT_SERVICE = bankAccountService;
        this.BCRYPT_PASSWORD_ENCODER = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpFilterChainSecurity) throws Exception {
        (httpFilterChainSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizationRequest -> authorizationRequest.anyRequest().permitAll()));
        return httpFilterChainSecurity.build();
    }

    protected void authenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(BCRYPT_PASSWORD_ENCODER);
        daoAuthenticationProvider.setUserDetailsService(BANK_ACCOUNT_SERVICE);
        return daoAuthenticationProvider;
    }
}
