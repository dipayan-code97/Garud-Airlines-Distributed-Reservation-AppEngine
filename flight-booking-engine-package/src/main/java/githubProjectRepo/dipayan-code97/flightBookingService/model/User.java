package githubProjectRepo.dipayan-code97.flightBookingService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author : Dipayan Paul
 * @created : 8/19/2023, Saturday
 **/
@Entity(name = "user")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "milesEarned")
    private Integer milesTravelled;

    public User(String username, String password, Integer milesTravelled) {
        this.username = username;
        this.password = password;
        this.milesTravelled = milesTravelled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMilesTravelled() {
        return this.milesTravelled;
    }

    public void setMilesTravelled(Integer milesTravelled) {
        this.milesTravelled = milesTravelled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        User user = (User) objectRef;
        return (Objects.equals(getUsername(), user.getUsername())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getMilesTravelled(), user.getMilesTravelled()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getUsername(),
                getPassword(), getMilesTravelled()));
    }

    @Override
    public String toString() {
        return ("User{" +
                "Username='" + username + '\'' +
                ", Password='" + password + '\'' +
                ", MilesTravelled=" + milesTravelled +
                '}');
    }
}
