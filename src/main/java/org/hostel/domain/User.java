package org.hostel.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.UserDto;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Role role;

    public User(UserDto user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
    }
}
