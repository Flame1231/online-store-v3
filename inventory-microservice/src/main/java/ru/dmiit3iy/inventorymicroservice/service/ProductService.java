package ru.dmiit3iy.inventorymicroservice.service;

import ru.dmiit3iy.inventorymicroservice.model.Product;

import java.util.List;

public interface ProductService {
    Product add(Product product);

    Product get(long id);

    List<Product> get();

    Product delete(long id);

    Product update(Product product);
}
