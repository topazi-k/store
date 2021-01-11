package com.urianskui.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urianskui.store.model.Product;
import com.urianskui.store.service.ProductService;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ProductStreamingResponseBody implements StreamingResponseBody {

    private String nameFilter;
    private ProductService service;

    private static final int RESULT_INCREMENT = 10000;

    public ProductStreamingResponseBody(String nameFilter, ProductService service) {
        this.nameFilter = nameFilter;
        this.service = service;
    }

    @Override
    public void writeTo(OutputStream response) {
        ObjectMapper mapper = new ObjectMapper();
        int firstResult = 0;
        while (true) {
            List<Product> products = service.getPaginatedProduct(firstResult);
            if (products.isEmpty()) {
                break;
            }
            service.filterProducts(nameFilter, products)
                    .forEach(product -> {
                        try {
                            String str = mapper.writeValueAsString(product) + "\n";
                            response.write(str.getBytes());
                            response.flush();
                        } catch (IOException e1) {

                        }
                    });
            firstResult += RESULT_INCREMENT;
        }
    }
}
