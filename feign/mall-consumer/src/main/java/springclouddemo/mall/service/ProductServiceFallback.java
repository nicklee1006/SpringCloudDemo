package springclouddemo.mall.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import springclouddemo.dto.Product;

import java.util.Collections;
import java.util.List;

@Component
public class ProductServiceFallback implements ProductService {

    @Override
    public List<Product> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Product loadByItemCode(@PathVariable("itemCode") String itemCode) {
        return new Product("error", "unknown", "fallback", 0);
    }
}
