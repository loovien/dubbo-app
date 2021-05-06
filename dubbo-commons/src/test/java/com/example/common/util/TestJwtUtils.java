package com.example.common.util;

import com.sun.tracing.dtrace.ArgsAttributes;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.util.Base64;


/**
 * created 5/6/2021 1:18 PM
 *
 * @author luowen <loovien@163.com>
 */
public class TestJwtUtils {

    @Test
    public void tokenTest() {
        String token = JwtUtils.token(11);
        System.out.println(token);
        token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiZXhwIjoxNjIyODc0MTQ1fQ.tlYHXhTKZ3J8UmE8TPocIoJb12cFVZ7SwiDqTXj-WVQ";
        System.out.println(JwtUtils.getId(token));
    }

}