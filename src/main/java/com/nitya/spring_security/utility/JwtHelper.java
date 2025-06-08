package com.nitya.spring_security.utility;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.nitya.spring_security.exceptions.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;

public class JwtHelper {

    private static final String SECRET = "xRont3J8BXnUlVP7WJDxEZcfSQ8IiMQRKI+tA7dzdMg=";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    private static final int MINUTES = 60;

        public static String generateToken(String email) {
            Instant now = Instant.now();
            return Jwts.builder()
                    .subject(email)
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                    .signWith(SECRET_KEY)
                    .compact();
        }

        public static String extractUsername(String token) {
            return getTokenBody(token).getSubject();
        }

        public static Boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }

        private static Claims getTokenBody(String token) {
            try {
                return Jwts.parser()
                        .verifyWith((SecretKey) SECRET_KEY)
//                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseSignedClaims(token)
//                        .parseClaimsJws(token)
                        .getPayload();
            } catch (JwtException e) {
                throw new AccessDeniedException("Access denied: " + e.getMessage());
            }
        }

        private static boolean isTokenExpired(String token) {
            Claims claims = getTokenBody(token);
            return claims.getExpiration().before(new Date());
        }

}
