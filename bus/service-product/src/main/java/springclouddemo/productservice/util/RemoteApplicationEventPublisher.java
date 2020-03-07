package springclouddemo.productservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.ApplicationContext;

public class RemoteApplicationEventPublisher {
    protected static Logger logger = LoggerFactory.getLogger(RemoteApplicationEventPublisher.class);

    public static void publishEvent(RemoteApplicationEvent event){
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
        if(null != context) {
            context.publishEvent(event);
            logger.info("Publish:{}", event);
        }else{
            logger.warn("Unable to get application context");
        }
    }
}
