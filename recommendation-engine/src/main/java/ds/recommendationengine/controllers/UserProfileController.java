package ds.recommendationengine.controllers;

import ds.model.UserProfile;
import ds.recommendationengine.redisstuff.redisclient.RedisCheckClient;
import ds.recommendationengine.services.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RedisCheckClient redisCheckClient;

    @GetMapping("/requestUserProfile/{id}")
    public ResponseEntity sendMessage(@PathVariable String id){
        final int waitTimeInSec = 2;

        logger.info("Sending message via Kafka requesting user profile id {}",id);
        userProfileService.sendMessage(id);
        timeOut(waitTimeInSec);
        return fetchProfileFromCache(id);
    }

    @GetMapping("/fetchUserProfile/{id}")
    public ResponseEntity fetchProfile(@PathVariable String id){
        return fetchProfileFromCache(id);
    }

    private void timeOut(int waitTimeInSec) {
        logger.info("Waiting for {} seconds (giving profile service time to update cache)",waitTimeInSec);
        try{
            TimeUnit.SECONDS.sleep(waitTimeInSec);
        } catch (Exception e){
            logger.error("Error during timeout");
        }
    }

    private ResponseEntity fetchProfileFromCache (String id){
        logger.info("Fetching user profile with id {}",id);
        UserProfile retrievedUser= redisCheckClient.getUserProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body("RETRIEVED user profile: "+retrievedUser+" from cache");
    }

}
