package ds.recommendationengine.kafkastuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleSourceBean {

    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishUserProfileRequest(String action, String usrId){
            logger.info("Sending Kafka message {} requesting for User Id: {}", action, usrId);

            UserProfileUpdateModel change = new UserProfileUpdateModel(
                    UserProfileUpdateModel.class.getTypeName(),
                    action,
                    usrId
            );

            source.output().send(MessageBuilder.withPayload(change).build());}
}