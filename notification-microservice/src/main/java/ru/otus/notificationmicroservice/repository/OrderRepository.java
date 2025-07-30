package ru.otus.notificationmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.notificationmicroservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
