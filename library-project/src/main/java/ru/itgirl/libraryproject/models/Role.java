package ru.itgirl.libraryproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Role implements GrantedAuthority {

    @Id
    private Long id;
    @Setter
    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;
    @Override
    public String getAuthority() {
        return getName();
    }
}
