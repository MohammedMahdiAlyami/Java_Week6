package com.example.hw28.Controller;

import com.example.hw28.Model.Product;
import com.example.hw28.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProduct(){
        List<Product> products= productService.getAllProduct();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product){
        productService.updateProduct(id,product);
        return ResponseEntity.status(200).body("Product updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product deleted");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id){
        Product product= productService.getProductByID(id);
        return ResponseEntity.status(200).body(product);
    }
}