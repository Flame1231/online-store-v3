package org.dmiit3iy.ordermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.client.InventoryClient;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.dmiit3iy.ordermicroservice.kafka.OrderProducer;
import org.dmiit3iy.ordermicroservice.model.OrderRequest;
import org.dmiit3iy.ordermicroservice.model.OrderResponse;
import org.dmiit3iy.ordermicroservice.model.User;
import org.dmiit3iy.ordermicroservice.model.dto.OrderEvent;
import org.dmiit3iy.ordermicroservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderProducer orderProducer;
    private final UserRepository userRepository;

    public OrderResponse processOrder(OrderRequest orderRequest, UserDetails userDetails) {
        ProductResponse productResponse = inventoryClient.checkProductAvailability(orderRequest.getOrderId());

        if (productResponse.getQuantity() < orderRequest.getQuantity()) {
            return new OrderResponse(null, "FAILED", "Not enough products in stock");
        }

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));


        BigDecimal totalPrice = new BigDecimal(productResponse.getPrice())
                .multiply(BigDecimal.ONE.subtract(new BigDecimal(productResponse.getSale())))
                .multiply(new BigDecimal(orderRequest.getQuantity()));

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        orderEvent.setUserId(user.getId());
        orderEvent.setProductId(orderRequest.getOrderId());
        orderEvent.setQuantity(orderRequest.getQuantity());
        orderEvent.setPrice(new BigDecimal(productResponse.getPrice()));
        orderEvent.setSale(new BigDecimal(productResponse.getSale()));
        orderEvent.setTotalPrice(totalPrice);


        orderProducer.send(orderEvent);

        return new OrderResponse(
                orderEvent.getOrderId(),
                "SUCCESS",
                "Order created successfully");
    }
}