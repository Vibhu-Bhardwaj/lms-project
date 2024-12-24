package org.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component

public class JwtUtils {

    private String jwtSecret = "asfdjvbfljknvkrljnjrvekbdvnrjkglfjknvjhfssfvnfjnvnsjkfnvjlhfknvfnvfjvnfjvnjfvfjfvnfjvsflsljkfvnv";

    private int jwtExpTime = 36000000;

    public Key generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateJwt(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpTime))
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(generateKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Invalid Jwt Token"+e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT Token is expired"+e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Jwt is unsupported"+e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Jwt string is empty"+e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error in Jwt"+e.getMessage());
        }
    }

    public String getUserNameFromJwt(String token){
        return Jwts.parserBuilder().setSigningKey(generateKey())
                .build().parseClaimsJws(token).getBody().getSubject();

        }

}
                    