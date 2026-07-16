package com.Osheen.VogueNest.service.impl;

import com.Osheen.VogueNest.model.Product;
import com.Osheen.VogueNest.repository.ProductRepository;
import com.Osheen.VogueNest.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String category) {
        if (category == null || category.equalsIgnoreCase("all") || category.trim().isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    public Product addProduct(Product product) {
        product.setId(null); // ID generation handled via database sequences
        return productRepository.save(product);

    }
    //for add-product updation
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void updateProduct(Product product) {
        // Save method ID check karke apne aap Update query chala dega
        productRepository.save(product);
    }
    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}