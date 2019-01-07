package ds.profileserver.controllers;

import ds.model.UserProfile;
import ds.profileserver.redisstuff.redisclient.RedisSaveClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisSaveClient redisSaveClient;

    @GetMapping("/putStartingData/{id}")
    public ResponseEntity sendMessage(@PathVariable String id){
        UserProfile mockedUpRetrievedUser = new UserProfile(id,new String[]{"3","3","3","3","3"});
        redisSaveClient.saveUserProfileToCache(mockedUpRetrievedUser);
        return ResponseEntity.status(HttpStatus.OK).body("Put user Profile in cache"+mockedUpRetrievedUser);
    }

}
