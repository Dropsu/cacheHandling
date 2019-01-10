package ds.profileserver.controllers;

import ds.model.UserProfile;
import ds.profileserver.cassandra.cassandrarepositories.CassUserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CassandraSetupController {

    @Autowired
    private CassandraOperations cassandraOperations;

    @Autowired
    private CassUserProfileRepository cassUserProfileRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/cassSetup")
    public ResponseEntity cassandraSetup(){
        cassUserProfileRepository.save(new UserProfile("7",new String[]{"7","7","7","7","7"}));
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
