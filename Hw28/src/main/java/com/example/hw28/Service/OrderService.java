package com.example.hw28.Service;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.Order;
import com.example.hw28.Model.Product;
import com.example.hw28.Model.User;
import com.example.hw28.Repository.OrderRepository;
import com.example.hw28.Repository.ProductRepository;
import com.example.hw28.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrder(Integer userid) {
        List<Order> orders = orderRepository.findOrderByUserId(userid);
        return orders;
    }

    public void addOrder(Integer userId,Integer productid,Order order) {
        Product product=productRepository.findProductById(productid);
        User user = userRepository.findUsersById(userId);
        order.setProduct(product);
        order.setUser(user);
        order.setTotalPrice(product.getPrice()*order.getQuantity());
        order.setStatus("NEW");
        orderRepository.save(order);
    }

    public void updateOrder(Integer id, Order order) {
        Order oldOrder = orderRepository.findOrderById(id);
        if (oldOrder == null) {
            throw new ApiException("Order not found");
        }
        oldOrder.setStatus(order.getStatus());
        oldOrder.setQuantity(order.getQuantity());
        oldOrder.setTotalPrice(order.getTotalPrice());
        oldOrder.setDateReceived(order.getDateReceived());
        orderRepository.save(oldOrder);
    }

    public void deleteOrder(Integer userid,Integer id) {
        Order oldOrder = orderRepository.findOrderById(id);
        if (oldOrder.getUser().getId()!=userid||oldOrder == null) {
            throw new ApiException("Order not found");
        }
        if (oldOrder.getStatus().equalsIgnoreCase("inProgress") || oldOrder.getStatus().equalsIgnoreCase("completed")) {
            throw new ApiException("Order inProgress");
        }
        orderRepository.delete(oldOrder);
    }

    public void changeStatus(Integer id, String status) {
        Order oldOrder = orderRepository.findOrderById(id);
        if (oldOrder == null) {
            throw new ApiException("Order not found");
        }
        oldOrder.setStatus(status);
        orderRepository.save(oldOrder);
    }

    public Order getOrderByID(Integer id){
        Order order =orderRepository.findOrderById(id);
        return order;
    }

}
