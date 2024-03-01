package com.dto.complex_demo.services;

import com.dto.complex_demo.dto.OrderDTO;
import com.dto.complex_demo.dto.OrderResponse;
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
import java.util.stream.Collectors;

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

    // hämta alla ordrar
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        // skapa en convert metod
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // hämta ordrar baserat på userId
    public List<OrderResponse> getUserOrders(String userId) {
        // här bör vi verkligen kolla att usern finns först!

        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }



    // hjälpmetod (util)
    private OrderResponse convertToDTO(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setUserId(order.getUser().getId());

        orderResponse.setOrderedProductIds(order.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        orderResponse.setOrderedDate(order.getOrderedDate());

        return orderResponse;
    }








}



























