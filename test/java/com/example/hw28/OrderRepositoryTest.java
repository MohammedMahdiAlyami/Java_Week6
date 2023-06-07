package com.example.hw28;

import com.example.hw28.Model.Order;
import com.example.hw28.Model.Product;
import com.example.hw28.Model.User;
import com.example.hw28.Repository.OrderRepository;
import com.example.hw28.Repository.ProductRepository;
import com.example.hw28.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    Order order, order1,order2,order3;
    Product product;
    User user;

    List<Order> orderList;

    @BeforeEach
    void setUp(){

        user = new User(null, "Maha", "Maha1122", "ADMIN", null);
        product = new Product(null, "phone", 2000,null);

        order1 = new Order(null, 1, 2000, new Date(), "new", user, product);
        order2 = new Order(null, 2, 3000, new Date(), "new", user, product);
        order3 = new Order(null, 3, 4000, new Date(), "new", user, product);
    }

    @Test
    public void findOrderById(){
        userRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);

        order = orderRepository.findOrderById(order1.getId());
        Assertions.assertThat(order).isEqualTo(order1);
    }

    @Test
    public void findOrdersByUser(){
        userRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        orderList = orderRepository.findOrderById(user);
        Assertions.assertThat(orderList.get(0).getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void findOrdersByProduct(){
        userRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        orderList = orderRepository.findOrdersByProduct(product);
        Assertions.assertThat(orderList.get(0).getProduct().getId()).isEqualTo(product.getId());
    }

}