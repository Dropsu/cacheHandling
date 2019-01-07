package ds.profileserver.redisstuff.redisrepositories;

import ds.profileserver.model.UserProfile;

public interface UserProfileRedisRepository {

    void saveUserProfile(UserProfile userProfile);

    void updateUserProfile(UserProfile userProfile);

    void deleteUserProfile(String userProfileId);

    UserProfile findUserProfile(String userProfileId);

}