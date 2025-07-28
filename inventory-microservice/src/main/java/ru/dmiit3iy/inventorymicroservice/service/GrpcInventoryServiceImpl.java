package ru.dmiit3iy.inventorymicroservice.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.InventoryServiceGrpc;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.ProductRequest;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.ProductResponse;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GrpcInventoryServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {

    private final InventoryHandler inventoryHandler;

    @Override
    public void checkAvailability(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        ProductResponse response = inventoryHandler.checkAvailability(request.getProductId());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}