package com.example.hw28;

import com.example.hw28.Model.Order;
import com.example.hw28.Model.Product;
import com.example.hw28.Model.User;
import com.example.hw28.Repository.OrderRepository;
import com.example.hw28.Repository.ProductRepository;
import com.example.hw28.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;

    Order order, order1,order2,order3;
    Product product1, product2, product3;
    User user;

    List<Product> productList;

    @BeforeEach
    void setUp(){

        user = new User(1, "Maha", "Maha1122", "ADMIN", null);
        product1 = new Product(null, "phone1", 2000,null);
        product2 = new Product(null, "phone2", 3000,null);
        product3 = new Product(null, "phone3", 4000,null);

        order1 = new Order(null, 1, 2000, new Date(), "new", user, product1);
        order2 = new Order(null, 2, 3000, new Date(), "new", user, product1);
        order3 = new Order(null, 3, 4000, new Date(), "new", user, product1);

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

    }

    @Test
    public void getProducts(){
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> productList1 = productService.getAllProduct();
        Assertions.assertEquals(productList1,productList);
        Assertions.assertEquals(productList1.size(),productList.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void addProduct(){
        productService.addProduct(product1);
        verify(productRepository,times(1)).save(product1);
    }

    @Test
    public void updateProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.updateProduct(product2,product1.getId());
        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).save(product1);
    }

    @Test
    public void deleteProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.deleteProduct(product1.getId());
        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).delete(product1);
    }

}