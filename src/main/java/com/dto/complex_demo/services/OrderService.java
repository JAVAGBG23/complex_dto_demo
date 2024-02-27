package com.dto.complex_demo.services;

import com.dto.complex_demo.dto.OrderDTO;
import com.dto.complex_demo.models.Order;
import com.dto.complex_demo.models.Product;
import com.dto.complex_demo.models.User;
import com.dto.complex_demo.repository.OrderRepository;
import com.dto.complex_demo.repository.ProductRepository;
import com.dto.complex_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        // lista med produkter, då måste mappa/loopa igenom listan och hitta idn
        List<Product> products = new ArrayList<>();
        for (String productId : orderDTO.getProductIds()) {
            products.add(productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product id")));
        }

        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setProducts(products);
        newOrder.setOrderedDate(orderDTO.getOrderedDate());

        return  orderRepository.save(newOrder);
    }




































}
