package edu.miu.cs489.studyplus.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jjt.secretKey}")
    public String SECRET;

    public String generateToken(UserDetails userDetails){
       return Jwts.builder()
                .issuedAt(new Date())
                .issuer("studyPlus.org")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(userDetails.getUsername())
                .claim(
                        "authorities",populateAuthorities(userDetails))
                .signWith(signInKey())
                .compact();
    }
    private SecretKey signInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }
    public String populateAuthorities(UserDetails userDetails){
       return  userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(signInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
