package springclouddemo.productservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springclouddemo.productservice.entity.ProductDto;
import springclouddemo.productservice.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEndpoint.class);

    @Autowired
    ProductService productService;

    @Value("${server.port}")
    private int serverPort = 0;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDto> list() {
        LOGGER.info("Server port {}", serverPort);

        return productService.findAll();
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public ProductDto detail(@PathVariable String itemCode) {
        LOGGER.info("Server port {}", serverPort);

        return productService.findOne(itemCode);
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.POST)
    public ProductDto save(@PathVariable String itemCode) {
        ProductDto productDto = this.productService.findOne(itemCode);

        if (null != productDto) {
            this.productService.save(productDto);
        }

        return productDto;
    }
}
