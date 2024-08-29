package dev.vlaship.book.catalog.mapper;

import org.mapstruct.*;
import dev.vlaship.book.catalog.dto.LoginRequest;
import dev.vlaship.book.catalog.dto.LoginResponse;
import dev.vlaship.book.catalog.dto.SignupRequest;
import dev.vlaship.book.catalog.model.User;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    @Mapping(source = "username", target = "name")
    @Mapping(source = "password", target = "password")
    User convert(LoginRequest req);

    @Mapping(source = "username", target = "name")
    @Mapping(source = "password", target = "password")
    User convert(SignupRequest req);

    LoginResponse convert(String token);

}
