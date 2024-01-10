package githubProjectRepo.dipayan-code97.flightBookingService.service;

import githubProjectRepo.dipayan-code97.flightBookingService.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Dipayan Paul
 * @created : 8/19/2023, Saturday
 **/
@Service
public class UserService implements UserDetailsService {

    private final UserRepository USER_REPOSITORY;
    public UserService(UserRepository USER_REPOSITORY) {
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    public UserRepository getUSER_REPOSITORY() {
        return this.USER_REPOSITORY;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (USER_REPOSITORY.findById(username).
                orElseThrow());
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        UserService that = (UserService) objectRef;
        return (Objects.equals(getUSER_REPOSITORY(), that.getUSER_REPOSITORY()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getUSER_REPOSITORY()));
    }

    @Override
    public String toString() {
        return ("UserService{" +
                "UserRepository=" + USER_REPOSITORY +
                '}');
    }
}
