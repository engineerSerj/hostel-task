package org.hostel.repositoriy;

import org.hostel.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

}
