package dev.zotware.apps.pts.auth.jwt;

import dev.zotware.apps.pts.auth.payload.TokenVerifyResponse;
import dev.zotware.apps.pts.auth.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${zotware.dev.app.jwtSecret}")
    private String jwtSecret;

    @Value("${zotware.dev.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKeyResolver(new SigningKeyResolverAdapter() {
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                return Keys.hmacShaKeyFor(jwtSecret.getBytes());
            }
        }).build().parseClaimsJws(token).getBody().getSubject();
    }

    public TokenVerifyResponse validateJwtToken(String authToken) {

        TokenVerifyResponse response = new TokenVerifyResponse("", true);

        try {
            Jwts.parserBuilder().setSigningKeyResolver(new SigningKeyResolverAdapter() {
                @Override
                public Key resolveSigningKey(JwsHeader header, Claims claims) {
                    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
                }
            }).build().parseClaimsJws(authToken);
            return response;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.error("Error: " + e.getMessage());
            response.setReason(e.getMessage());
            response.setValid(false);
        }

        return response;
    }
}
