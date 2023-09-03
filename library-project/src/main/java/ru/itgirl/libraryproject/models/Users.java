package ru.itgirl.libraryproject.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Size(min=2, message="не меньше 5 знаков")
    @Column(nullable = false)
    private String login;

    @Column(nullable=false)
    private String password;

    //@ManyToMany(fetch=FetchType.EAGER)
    @ManyToMany
    @JoinTable(
            name = "role_user",
            inverseJoinColumns = @JoinColumn (name = "role_id",referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
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
}
