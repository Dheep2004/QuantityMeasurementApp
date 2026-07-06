package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                jwtSecret.getBytes(StandardCharsets.UTF_8)
        );

    }

    public String generateToken(
            String email,
            String name,
            String picture
    ) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("name", name);
        claims.put("picture", picture);

        return createToken(claims, email);

    }

    private String createToken(
            Map<String, Object> claims,
            String subject
    ) {

        Date now = new Date();

        Date expiry = new Date(
                now.getTime() + jwtExpiration
        );

        return Jwts.builder()

                .setClaims(claims)

                .setSubject(subject)

                .setIssuedAt(now)

                .setExpiration(expiry)

                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )

                .compact();

    }

    public String extractUsername(
            String token
    ) {

        return extractClaim(
                token,
                Claims::getSubject
        );

    }

    public Date extractExpiration(
            String token
    ) {

        return extractClaim(
                token,
                Claims::getExpiration
        );

    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        final Claims claims =
                extractAllClaims(token);

        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(
            String token
    ) {

        return Jwts.parserBuilder()

                .setSigningKey(
                        getSigningKey()
                )

                .build()

                .parseClaimsJws(token)

                .getBody();

    }

    public boolean isTokenExpired(
            String token
    ) {

        return extractExpiration(token)

                .before(new Date());

    }

    public boolean isTokenValid(
            String token,
            String email
    ) {

        return extractUsername(token)

                .equals(email)

                &&

                !isTokenExpired(token);

    }

}