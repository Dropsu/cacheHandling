package ds.recommendationengine.redisstuff.redisrepositories;


import ds.recommendationengine.model.UserProfile;

public interface UserProfileRedisRepository {

    void saveUserProfile(UserProfile userProfile);

    void updateUserProfile(UserProfile userProfile);

    void deleteUserProfile(String userProfileId);

    UserProfile findUserProfile(String userProfileId);

}