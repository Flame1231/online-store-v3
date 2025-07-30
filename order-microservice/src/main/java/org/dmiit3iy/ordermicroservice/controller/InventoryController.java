package org.dmiit3iy.ordermicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.dmiit3iy.ordermicroservice.mapper.ProductMapper;
import org.dmiit3iy.ordermicroservice.model.dto.ProductDTO;
import org.dmiit3iy.ordermicroservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryClient;
    private final ProductMapper productMapper;


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getItem(@PathVariable long id) {
        ProductResponse productResponse = inventoryClient.checkProduct(id);
        ProductDTO productDTO = productMapper.toDto(productResponse);
        return ResponseEntity.ok(productDTO);
    }
}


