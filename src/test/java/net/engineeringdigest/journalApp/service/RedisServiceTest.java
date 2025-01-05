package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.cache.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@Slf4j
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public  void  testRedis(){
        redisTemplate.opsForValue().set("ani","hello");
        String value = (String) redisTemplate.opsForValue().get("kunal");
        log.info("value={}",value);
    }
}
