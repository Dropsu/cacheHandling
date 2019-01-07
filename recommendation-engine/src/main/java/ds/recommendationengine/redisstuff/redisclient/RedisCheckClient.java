package ds.recommendationengine.redisstuff.redisclient;


import ds.recommendationengine.model.UserProfile;
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

    private UserProfile checkRedisCache(String userId) {
        try {
            return userProfileRedisRepository.findUserProfile(userId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve user profile {} check Redis Cache.  Exception {}", userId, ex);
            return null;
        }
    }

    public UserProfile getUserProfile(String userId){
        logger.debug("Requesting UserProfile with id: {}", userId);

        UserProfile usr = checkRedisCache(userId);

        if (usr!=null){
            logger.debug("I have successfully retrieved an user profile {} from the redis cache: {}", userId, usr);
            return usr;
        }

        logger.debug("Unable to locate user profile from the redis cache: {}.", userId);
        return null;
    }


}