package springclouddemo.productservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springclouddemo.dto.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEndpoint.class);

    private List<Product> products;

    @Value("${server.port}")
    private int serverPort = 0;

    public ProductEndpoint() {
        products = new ArrayList<>();
        products.add(new Product("item-1", "test-1", "brand1", 100));
        products.add(new Product("item-2", "test-2", "brand2", 200));
        products.add(new Product("item-3", "test-3", "brand3", 300));
        products.add(new Product("item-4", "test-4", "brand4", 400));
        products.add(new Product("item-5", "test-5", "brand5", 500));
        products.add(new Product("item-6", "test-6", "brand6", 600));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        LOGGER.info("Server port {}", serverPort);

        return this.products;
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public Product detail(@PathVariable String itemCode) {
        LOGGER.info("Server port {}", serverPort);

        for (Product product : products) {
            if (product.getItemCode().equalsIgnoreCase(itemCode))
                return product;
        }
        return null;
    }
}
