package in.bm.Netflix_plan_service.CONFIGURATION;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());


        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(5))
                .serializeKeysWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer
                                        (new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer
                                        (new GenericJackson2JsonRedisSerializer(mapper)));

        Map<String,RedisCacheConfiguration> cacheConfig = new HashMap<>();
        cacheConfig.put("plan",cacheConfiguration.entryTtl(Duration.ofDays(60)));
        cacheConfig.put("plans",cacheConfiguration.entryTtl(Duration.ofDays(60)));


        return RedisCacheManager
                .builder(connectionFactory)
                .withInitialCacheConfigurations(cacheConfig)
                .cacheDefaults(cacheConfiguration)
                .build();

    }
}
