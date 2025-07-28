package ru.dmiit3iy.inventorymicroservice.service;

import lombok.RequiredArgsConstructor;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.ProductResponse;
import org.springframework.stereotype.Component;
import ru.dmiit3iy.inventorymicroservice.model.Product;
import ru.dmiit3iy.inventorymicroservice.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class InventoryHandler {

    private final ProductRepository productRepository;

    public ProductResponse checkAvailability(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice().toString())
                .setSale(product.getSale().toString())
                .build();
    }
}