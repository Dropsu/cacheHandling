package ds.recommendationengine.controllers;

import ds.recommendationengine.services.UserProfileService;
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

    @Autowired
    private UserProfileService userProfileService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/sendMessage/{id}")
    public ResponseEntity sendMessage(@PathVariable String id){
        logger.info("Sent message with id {}",id);
        userProfileService.sendMessage(id);
        return ResponseEntity.status(HttpStatus.OK).body("MESSAGE 'UPDATE' sent with USERID:"+id);


    }

}
