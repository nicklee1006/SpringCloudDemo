package springclouddemo.mall.service;

import org.springframework.stereotype.Component;
import springclouddemo.mall.entity.Product;

import java.util.Collections;
import java.util.List;

@Component
public class ProductServiceFallback implements ProductService {
    @Override
    public List<Product> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Product loadByItemCode(String itemCode) {
        return new Product("error", "Unknown", "Unknown", 0);
    }
}
