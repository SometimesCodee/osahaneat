package com.example.osahaneatMysql.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;

    //generate ra token va ma hoa no
    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }

    //giai ma token
    public boolean verifyToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
