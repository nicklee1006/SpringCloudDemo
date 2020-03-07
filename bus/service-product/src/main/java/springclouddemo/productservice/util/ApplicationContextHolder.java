package springclouddemo.productservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext = null;

    @Override
    public void destroy() throws Exception {
        ApplicationContextHolder.clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("Inject ApplicationContext to SpringContextHolder: {}", applicationContext);

        if (ApplicationContextHolder.applicationContext != null) {
            logger.warn("SpringContextHolder's ApplicationContext is overwritten, old ApplicationContext is: {}", ApplicationContextHolder.applicationContext);
        }

        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static void clearHolder() {
        logger.debug("Clear SpringContextHolder's ApplicationContext: {}", applicationContext);
        applicationContext = null;
    }

    private static void assertContextInjected() {
        Assert.notNull(applicationContext, "applicaitonContext not injected, define SpringContextHolder in applicationContext..");
    }
}
