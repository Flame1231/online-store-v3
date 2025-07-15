package org.dmiit3iy.ordermicroservice.model.mapper;

import org.dmiit3iy.ordermicroservice.model.User;
import org.dmiit3iy.ordermicroservice.model.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "USER")
    User toUser(RegistrationRequest registrationRequest);


}