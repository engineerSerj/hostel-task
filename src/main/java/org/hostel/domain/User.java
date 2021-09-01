package org.hostel.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.RegistredUserDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(RegistredUserDto user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}
