package ds.profileserver.redisstuff.redisclient;


import ds.profileserver.model.UserProfile;
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

    //FETCHING DATA FROM REDIS
    private UserProfile checkRedisCache(String userId) {
        try {
            return userProfileRedisRepository.findUserProfile(userId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve user profile {} check Redis Cache.  Exception {}", userId, ex);
            return null;
        }
    }

    //SAVING DATA FROM REDIS
    private void cacheUserProfileObject(UserProfile org) {
        try {
            userProfileRedisRepository.saveUserProfile(org);
        }catch (Exception ex){
            logger.error("Unable to cache user profile {} in Redis. Exception {}", org.getId(), ex);
        }
    }

    public UserProfile getUserProfile(String userId){
        logger.debug("In getUserProfile: {}", 5);

        UserProfile usr = checkRedisCache(userId);

        if (usr!=null){
            logger.debug("I have successfully retrieved an user profile {} from the redis cache: {}", userId, usr);
            return usr;
        }

        logger.debug("Unable to locate user profile from the redis cache: {}.", userId);

        /*Save the record to cache*/
        UserProfile usrToSave = new UserProfile(15);

        cacheUserProfileObject(usrToSave);

        return usr;
    }


}