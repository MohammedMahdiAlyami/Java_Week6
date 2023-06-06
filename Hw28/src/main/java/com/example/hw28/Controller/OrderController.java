package com.example.hw28.Controller;

import com.example.hw28.Model.Order;
import com.example.hw28.Model.User;
import com.example.hw28.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomerOrders(@AuthenticationPrincipal User user) {
        List<Order> orders = orderService.getAllOrder(user.getId());
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/add/{productid}")
    public ResponseEntity addOrder(@AuthenticationPrincipal User user, @PathVariable Integer productid, @RequestBody @Valid Order order) {
        orderService.addOrder(user.getId(), productid, order);
        return ResponseEntity.status(200).body("Order added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer  id, @RequestBody @Valid Order order){
        orderService.updateOrder(id,order);
        return ResponseEntity.status(200).body("Order updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal User user,@PathVariable Integer  id){
        orderService.deleteOrder(user.getId(), id);
        return ResponseEntity.status(200).body("Order deleted");

    }

    @PutMapping("/changestatus/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable Integer id ,@PathVariable String status){
        orderService.changeStatus(id,status);
        return ResponseEntity.status(200).body("status changed");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id){
        Order order= orderService.getOrderByID(id);
        return ResponseEntity.status(200).body(order);
    }

}