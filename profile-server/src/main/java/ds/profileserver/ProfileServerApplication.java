package ds.profileserver;

import ds.model.UserProfile;
import ds.profileserver.kafkastuff.UserProfileUpdateModel;
import ds.profileserver.redisstuff.redisclient.RedisSaveClient;
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

import java.util.concurrent.TimeUnit;

@EnableBinding(Sink.class)
@SpringBootApplication
public class ProfileServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServerApplication.class);

    @Autowired
    private RedisSaveClient redisSaveClient;

    //KAFKA STUFF:
    @StreamListener(Sink.INPUT)
    public void loggerSink(UserProfileUpdateModel userProfileUpdateModel){
        String id = userProfileUpdateModel.getUserId();
        logger.info("Received message for user id {} with action {}",id,userProfileUpdateModel.getAction());
        handleUserProfileRequest(id);
    }

    //REDIS STUFF:

    @Bean
    public static JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnFactory = new
                JedisConnectionFactory();
        jedisConnFactory.setHostName(ServiceConfig.getRedisServer());
        jedisConnFactory.setPort(ServiceConfig.getRedisPort());
        return jedisConnFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String,Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    private void handleUserProfileRequest (String id){
        final int waitTimeInSec = 10;
        timeOut(waitTimeInSec);

        UserProfile mockedUpRetrievedUser = new UserProfile(id,new String[]{"7","7","7","7","7"});

        redisSaveClient.saveUserProfileToCache(mockedUpRetrievedUser);
    }

    private void timeOut(int waitTimeInSec) {
        logger.info("Fetching user profile from database which takes {} seconds",waitTimeInSec );
        try{
            TimeUnit.SECONDS.sleep(waitTimeInSec);
        } catch (Exception e){
            logger.error("Error during timeout");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ProfileServerApplication.class, args);
    }
}
