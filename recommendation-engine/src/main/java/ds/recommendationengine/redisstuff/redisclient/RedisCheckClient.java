package ds.recommendationengine.redisstuff.redisclient;


import ds.model.UserProfile;
import ds.recommendationengine.redisstuff.redisrepositories.UserProfileRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisCheckClient {

    @Autowired
    UserProfileRedisRepository userProfileRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(RedisCheckClient.class);


    public UserProfile getUserProfile(String userId){
        logger.info("Requesting UserProfile with id: {} from cache", userId);

        UserProfile usr = checkRedisCache(userId);

        if (usr!=null){
            logger.info("I have successfully retrieved an user profile {} from the redis cache: {}", userId, usr);
            return usr;
        }

        logger.info("Unable to locate user profile from the redis cache: {}.", userId);
        return null;
    }

    private UserProfile checkRedisCache(String userId) {
        try {
            return userProfileRedisRepository.findUserProfile(userId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve user profile {} check Redis Cache.  Exception {}", userId, ex);
            return null;
        }
    }

}