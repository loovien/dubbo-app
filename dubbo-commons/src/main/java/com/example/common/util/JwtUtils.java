package com.example.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * created 5/6/2021 10:55 AM
 *
 * @author luowen <loovien@163.com>
 */
final public class JwtUtils {

    private static final String secretKey = "24BH4AAGNMZmbp6J8eb2lyHrOTIwkTLhj+gqsftq49g=";

    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

    // private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String token(Integer uid) {

        Instant instant = LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        return Jwts.builder().setId(String.valueOf(uid)).setExpiration(Date.from(instant))
                .signWith(key)
                .compact();
    }

    public static Integer getId(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return Integer.parseInt(claimsJws.getBody().getId());
    }
}
