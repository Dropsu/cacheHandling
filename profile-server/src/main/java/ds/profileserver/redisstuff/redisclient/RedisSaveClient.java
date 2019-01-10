package ds.profileserver.redisstuff.redisclient;


import ds.model.UserProfile;
import ds.profileserver.redisstuff.redisrepositories.UserProfileRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisSaveClient {

    @Autowired
    UserProfileRedisRepository userProfileRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(RedisSaveClient.class);

    public void saveUserProfileToCache(UserProfile userProfileToSave){
        logger.info("Saving UserProfile with id: {} to cache", userProfileToSave.getId());
        cacheUserProfileObject(userProfileToSave);
    }

    private void cacheUserProfileObject(UserProfile org) {
        try {
            userProfileRedisRepository.saveUserProfile(org);
        } catch (Exception ex){
            logger.error("Unable to cache user profile {} in Redis. Exception {}", org.getId(), ex);
        }
    }


}