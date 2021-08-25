package net.minchul.api.security.domain;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log
public class SecurityToken {

    private final String token; // 내부에서 랜덤 값으로 만들어줌
    private final String key;

    public SecurityToken(String key) {
        this.token = createToken();
        this.key = key;
    }

    private String createToken() {

        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put("alg", "HS2256");
            headers.put("typ", "JWT");

            Map<String, Object> payload = new HashMap<>();
            payload.put("data", "dummy"); // 실제로는 프론트와 상의해서 넣어줘야함.

            long expirationTime = 1000 * 6L * 2L;

            Date ext = new Date();
            ext.setTime(ext.getTime() + expirationTime);

            return Jwts.builder()
                    .setHeader(headers)
                    .setClaims(payload)
                    .setSubject("user")
                    .setExpiration(ext)
                    .signWith(SignatureAlgorithm.ES256, key.getBytes())
                    .compact();
        }catch (SecurityException e){
            log.info("SecurityException JWT");
        }catch (MalformedJwtException e){
            log.info("MalformedJwtException JWT");
        }catch (ExpiredJwtException e){
            log.info("ExpiredJwtException JWT");
        }catch (UnsupportedJwtException e){
            log.info("UnsupportedJwtException JWT");
        }catch (IllegalAccessError e){
            log.info("IllegalAccessError JWT");
        }return null;
    }
}
