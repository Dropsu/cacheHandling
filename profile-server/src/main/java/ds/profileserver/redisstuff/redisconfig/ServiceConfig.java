package ds.profileserver.redisstuff.redisconfig;

import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {


  private static String redisServer="localhost";

  private static String redisPort="6379";

  public static String getRedisServer(){
    return redisServer;
  }

  public static Integer getRedisPort(){
    return Integer.parseInt( redisPort );
  }

}