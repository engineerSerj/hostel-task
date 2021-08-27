package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.User;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class UserDto {
    private String token;
    private final String type = "Bearer";
    private String refreshToken;
    private long id;
    private String username;
    private List <String> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles().stream().map(role->role.getRoleName().name()).collect(Collectors.toList());
    }

    public UserDto(String accessToken, String refreshToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
