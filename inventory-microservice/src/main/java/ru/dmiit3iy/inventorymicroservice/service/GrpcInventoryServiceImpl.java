package ru.dmiit3iy.inventorymicroservice.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.InventoryServiceGrpc;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.ProductRequest;
import org.dmiit3iy.inventorymicroservice.grpc.inventory.ProductResponse;
import ru.dmiit3iy.inventorymicroservice.model.Product;
import ru.dmiit3iy.inventorymicroservice.repository.ProductRepository;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GrpcInventoryServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {

    private final ProductRepository productRepository;

    @Override
    public void checkAvailability(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductResponse response = ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice().toString())
                .setSale(product.getSale().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
