package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RefreshTokenUserDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public RefreshTokenUserDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
