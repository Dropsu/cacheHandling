package ds.profileserver;

import ds.profileserver.kafkastuff.UserProfileUpdateModel;
import ds.profileserver.redisstuff.redisconfig.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableBinding(Sink.class)
@SpringBootApplication
public class ProfileServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServerApplication.class);

    //KAFKA STUFF:

    @StreamListener(Sink.INPUT)
    public void loggerSink(UserProfileUpdateModel userProfileUpdateModel){
        logger.info("Received message for user id {} with action {}",userProfileUpdateModel.getUserId(),userProfileUpdateModel.getAction());
    }

    //REDIS STUFF:

    @Autowired
    private ServiceConfig serviceConfig;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnFactory = new
                JedisConnectionFactory();
        jedisConnFactory.setHostName(serviceConfig.getRedisServer());
        jedisConnFactory.setPort(serviceConfig.getRedisPort());
        return jedisConnFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String,Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProfileServerApplication.class, args);
    }
}
