package ds.profileserver.redisstuff.redisrepositories;

import ds.profileserver.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserProfileRedisRepositoryImpl implements UserProfileRedisRepository {
    private static final String HASH_NAME ="userProfile";

    private RedisTemplate<String, UserProfile> redisTemplate;
    private HashOperations hashOperations;

    public UserProfileRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private UserProfileRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void saveUserProfile(UserProfile org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void updateUserProfile(UserProfile org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void deleteUserProfile(String userProfileId) {
        hashOperations.delete(HASH_NAME, userProfileId);
    }

    @Override
    public UserProfile findUserProfile(String userProfileId) {
       return (UserProfile) hashOperations.get(HASH_NAME, userProfileId);
    }
}