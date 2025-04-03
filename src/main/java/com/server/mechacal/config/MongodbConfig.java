package com.server.mechacal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.server.mechacal.utils.UserRoleToIntegerConverter;
import com.server.mechacal.utils.IntegerToUserRoleConverter;

import java.util.Arrays;

@Configuration
public class MongodbConfig {
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
            new UserRoleToIntegerConverter(),
            new IntegerToUserRoleConverter()
        ));
    }
}
