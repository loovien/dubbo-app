package com.example.gateway;

import com.example.common.dto.gateway.ConnDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * created 5/8/2021 5:53 PM
 *
 * @author luowen <loovien@163.com>
 */
public class MapperTest {

    @Test
    void mapperTest1() throws IOException {
        String token = "{\"token\":\"abc123\"}";
        ObjectMapper objectMapper = getObjectMapper();
        ConnDTO connDTO = objectMapper.readValue(token.getBytes(StandardCharsets.UTF_8), ConnDTO.class);
        System.out.println(connDTO);
    }

    protected ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
