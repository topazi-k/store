package com.urianskui.store.service;

import com.urianskui.store.dao.ProductRepositoryJpa;
import com.urianskui.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private ProductRepositoryJpa productRepo;

    @Autowired
    public ProductService(ProductRepositoryJpa productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getPaginatedProduct(int firstResult) {
        return productRepo.findPaginated(firstResult);
    }

    public List<Product> filterProducts(String nameFilter, Collection<Product> products) {
        return products.stream()
                .filter(product -> !product.getName().matches(nameFilter))
                .collect(toList());
    }
}
