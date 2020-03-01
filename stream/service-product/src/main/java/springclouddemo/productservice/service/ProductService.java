package springclouddemo.productservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import springclouddemo.productservice.entity.ProductDto;
import springclouddemo.productservice.message.ProductMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    private Source source;
    private List<ProductDto> productList;

    @Autowired
    public ProductService(Source source) {
        this.source = source;
        this.productList = this.buildProducts();
    }

    /**
     * Get product list
     */
    public List<ProductDto> findAll() {
        return this.productList;
    }

    /**
     * get product by item
     */
    public ProductDto findOne(String itemCode) {
        for (ProductDto productDto : this.productList) {
            if (productDto.getItemCode().equalsIgnoreCase(itemCode))
                return productDto;
        }

        return null;
    }

    /**
     * update or save product
     */
    public ProductDto save(ProductDto productDto) {
        for (ProductDto sourceProductDto : this.productList) {
            if (sourceProductDto.getItemCode().equalsIgnoreCase(productDto.getItemCode())) {
                sourceProductDto.setName(sourceProductDto.getName() + "-new");
                sourceProductDto.setPrice(sourceProductDto.getPrice() + 100);
                productDto = sourceProductDto;
                break;
            }
        }

        // send message
        this.sendMsg(ProductMessage.MA_UPDATE, productDto.getItemCode());

        return productDto;
    }

    /**
     * Implement send message
     */
    protected void sendMsg(String msgAction, String itemCode) {
        ProductMessage productMsg = new ProductMessage(msgAction, itemCode);
        this.logger.info("Send message:{} ", productMsg);

        // Send
        this.source.output().send(MessageBuilder.withPayload(productMsg).build());
    }

    protected List<ProductDto> buildProducts() {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto("item-1", "test-1", "brand1", 100));
        products.add(new ProductDto("item-2", "test-2", "brand2", 200));
        products.add(new ProductDto("item-3", "test-3", "brand3", 300));
        products.add(new ProductDto("item-4", "test-4", "brand4", 400));
        products.add(new ProductDto("item-5", "test-5", "brand5", 500));
        products.add(new ProductDto("item-6", "test-6", "brand6", 600));

        return products;
    }
}
