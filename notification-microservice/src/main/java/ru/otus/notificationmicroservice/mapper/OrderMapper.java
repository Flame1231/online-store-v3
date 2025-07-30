package ru.otus.notificationmicroservice.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import ru.otus.notificationmicroservice.model.Order;
import ru.otus.notificationmicroservice.model.OrderMessageDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderMessageDto orderMessageDto);
}
