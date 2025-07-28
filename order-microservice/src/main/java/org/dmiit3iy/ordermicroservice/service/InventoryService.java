package org.dmiit3iy.ordermicroservice.service;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dmiit3iy.ordermicroservice.grpc.inventory.InventoryServiceGrpc;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductRequest;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.dmiit3iy.ordermicroservice.mapper.ProductMapper;
import org.dmiit3iy.ordermicroservice.model.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @GrpcClient("inventory-service")
    private final InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub;

    private final ProductMapper productMapper;

    public ProductDTO checkProduct(Long productId) {
        ProductRequest request = ProductRequest.newBuilder()
                .setProductId(productId)
                .build();

        ProductResponse response = inventoryStub.checkAvailability(request);
        return productMapper.toDto(response);
    }
}
