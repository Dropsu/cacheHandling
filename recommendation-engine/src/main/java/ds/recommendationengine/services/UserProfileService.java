package ds.recommendationengine.services;

import ds.recommendationengine.kafkastuff.SimpleSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    SimpleSourceBean simpleSourceBean;

    public void sendMessage (String id){
        simpleSourceBean.publishUserProfileRequest("UPDATE", id);
    }
}