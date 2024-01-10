package githubProjectRepo.dipayan-code97.bankingService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new (BCryptPasswordEncoder());
    }
}
