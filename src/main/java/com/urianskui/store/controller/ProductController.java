package com.urianskui.store.controller;

import com.urianskui.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/shop/product")
public class ProductController {


    private ProductService service;


    @Autowired
    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping
    public ResponseEntity<StreamingResponseBody> getProductsByNameFilter(@RequestParam(name = "nameFilter") String nameFilter) {
        if (nameFilter.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StreamingResponseBody responseBody = new ProductStreamingResponseBody(nameFilter, service);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
    }

    @ExceptionHandler
    public void handle(){ ;
    }

}
