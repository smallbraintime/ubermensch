package com.pollub.ubermensch;

import com.pollub.ubermensch.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class JwtServiceTest {
    private JwtService jwtService;

    private final String secret = "thisIsASecretKeyForJwtThatIsAtLeast32BytesLong!";
    private final long expirationMs = 1000 * 60;

    @BeforeEach
    void setup() {
        jwtService = new JwtService(secret, expirationMs);
    }

    @Test
    @DisplayName("generateToken() should create a valid JWT containing expected claims")
    void testGenerateToken() {
        Long userId = 123L;
        String email = "user@example.com";
        String role = "ADMIN";

        String token = jwtService.generateToken(userId, email, role);

        assertThat(token).isNotBlank();

        Claims claims = jwtService.validateToken(token);

        assertThat(claims.getSubject()).isEqualTo("123");
        assertThat(claims.get("email", String.class)).isEqualTo(email);
        assertThat(claims.get("role", String.class)).isEqualTo(role);

        assertThat(claims.getExpiration()).isAfter(claims.getIssuedAt());
    }

    @Test
    @DisplayName("validateToken() should return claims when token is valid")
    void testValidateToken() {
        String token = jwtService.generateToken(1L, "a@b.com", "USER");
        Claims claims = jwtService.validateToken(token);

        assertThat(claims).isNotNull();
        assertThat(claims.getSubject()).isEqualTo("1");
        assertThat(claims.get("email", String.class)).isEqualTo("a@b.com");
        assertThat(claims.get("role", String.class)).isEqualTo("USER");
    }

    @Test
    @DisplayName("validateToken() should throw if token signature is invalid")
    void testInvalidTokenSignature() {
        String token = jwtService.generateToken(1L, "a@b.com", "USER");

        String tampered = token.substring(0, token.length() - 2) + "xx";

        assertThatThrownBy(() -> jwtService.validateToken(tampered))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("JWT");
    }

    @Test
    @DisplayName("validateToken() should throw when token is expired")
    void testExpiredToken() throws InterruptedException {
        JwtService shortLivedService = new JwtService(secret, 1);

        String token = shortLivedService.generateToken(1L, "a@b.com", "USER");

        Thread.sleep(5);

        assertThatThrownBy(() -> shortLivedService.validateToken(token))
                .isInstanceOf(ExpiredJwtException.class);
    }
}
