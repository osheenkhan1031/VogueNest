package com.Osheen.VogueNest.service;

import com.Osheen.VogueNest.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getProducts(String category);
    Product addProduct(Product product);

    //update the add-product page
    Product getProductById(Long id);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}