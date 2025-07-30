package org.dmiit3iy.ordermicroservice.client;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dmiit3iy.ordermicroservice.grpc.inventory.InventoryServiceGrpc;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductRequest;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.dmiit3iy.ordermicroservice.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    @GrpcClient("inventory-service")
    private final InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub;

    private final ProductMapper productMapper;

    public ProductResponse checkProductAvailability(Long productId) {
        ProductRequest request = ProductRequest.newBuilder()
                .setProductId(productId)
                .build();
        return inventoryStub.checkAvailability(request);
    }
}