package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.RefreshToken;
import org.hostel.domain.User;
import org.hostel.dto.RefreshTokenUserDto;
import org.hostel.exception.RefreshTokenNotFoundException;
import org.hostel.exception.TokenRefreshException;
import org.hostel.exception.UserNotFoundException;
import org.hostel.repositoriy.RefreshTokenRepository;
import org.hostel.repositoriy.UserRepository;
import org.hostel.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    @Value("${jwt.token.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) throws UserNotFoundException {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId)));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    public ResponseEntity<RefreshTokenUserDto> refreshtoken(RefreshTokenUserDto refreshTokenRequest) throws RefreshTokenNotFoundException, TokenRefreshException {
        String requestRefreshToken = refreshTokenRequest.getRefreshToken();
        RefreshToken refreshToken = findByToken (requestRefreshToken).orElseThrow(() -> new RefreshTokenNotFoundException(requestRefreshToken));
        refreshToken = verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        String token = jwtUtils.generateTokenFromUsername(user.getUsername());
        logger.info("refreshTokenRequest updated");
        return ResponseEntity.ok(new RefreshTokenUserDto(token, requestRefreshToken));
    }
}
