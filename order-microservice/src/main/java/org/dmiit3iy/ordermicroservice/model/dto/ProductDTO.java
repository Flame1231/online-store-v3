package org.dmiit3iy.ordermicroservice.model.dto;

public record ProductDTO(
        long id,
        String name,
        int quantity,
        String price,
        String sale
) {
}