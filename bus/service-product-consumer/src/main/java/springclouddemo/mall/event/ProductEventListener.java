package springclouddemo.mall.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import springclouddemo.mall.entity.Product;
import springclouddemo.mall.service.ProductService;

@Component
public class ProductEventListener implements ApplicationListener<ProductEvent> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("productServiceFallback")
    @Autowired
    protected ProductService productService;

    @Override
    public void onApplicationEvent(ProductEvent productEvent) {
        if (ProductEvent.ET_UPDATE.equalsIgnoreCase(productEvent.getAction())) {
            this.logger.info("Received update event itemCode: {}", productEvent.getItemCode());
            // get new product info
            Product productDto = this.productService.loadByItemCode(productEvent.getItemCode());
            if (null != productDto)
                this.logger.info("Update product info:{}", productDto);
            else
                this.logger.info("itemCode:{} no exist", productEvent.getItemCode());
        } else if (ProductEvent.ET_DELETE.equalsIgnoreCase(productEvent.getAction())) {
            this.logger.info("Received delete event itemCode: {}", productEvent.getItemCode());
        } else {
            this.logger.info("Unknown product event: {}", productEvent);
        }
    }
}
