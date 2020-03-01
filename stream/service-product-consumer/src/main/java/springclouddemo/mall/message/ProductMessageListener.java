package springclouddemo.mall.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import springclouddemo.mall.entity.Product;
import springclouddemo.mall.service.ProductService;

@EnableBinding(Sink.class)
public class ProductMessageListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("productServiceFallback")
    @Autowired
    protected ProductService productService;

    @StreamListener(Sink.INPUT)
    public void onProductMessage(ProductMessage productMsg) {
        if (ProductMessage.MA_UPDATE.equalsIgnoreCase(productMsg.getAction())) {
            this.logger.info("Receive product update message, item code: {}", productMsg.getItemCode());
            // request new product info
            Product productDto = this.productService.loadByItemCode(productMsg.getItemCode());
            if (null != productDto)
                this.logger.info("Update product info:{}", productDto);
            else
                this.logger.info("Item code:{} doesn't exist", productMsg.getItemCode());
        } else if (ProductMessage.MA_DELETE.equalsIgnoreCase(productMsg.getAction())) {
            this.logger.info("Receive product delete message: {}", productMsg.getItemCode());
        } else {
            this.logger.info("Unknown product info: {}", productMsg);
        }
    }
}
