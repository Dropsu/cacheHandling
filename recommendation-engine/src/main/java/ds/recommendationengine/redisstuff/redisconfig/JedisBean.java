package ds.recommendationengine.redisstuff.redisconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class JedisBean {

    @Autowired
    private ServiceConfig serviceConfig;

    @Bean
    public static JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnFactory = new
                JedisConnectionFactory();
        jedisConnFactory.setHostName(ServiceConfig.getRedisServer());
        jedisConnFactory.setPort(ServiceConfig.getRedisPort());
        return jedisConnFactory;
    }

}
