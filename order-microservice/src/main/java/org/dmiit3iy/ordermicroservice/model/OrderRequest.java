package org.dmiit3iy.ordermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private long orderId;
    private String name;
    private int quantity;
    private int userId;
}
