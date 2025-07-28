package org.dmiit3iy.ordermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.client.InventoryClient;
import org.dmiit3iy.ordermicroservice.model.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryClient inventoryClient;

    public ProductDTO checkProduct(Long productId) {
        return inventoryClient.checkProductAvailability(productId);
    }
}