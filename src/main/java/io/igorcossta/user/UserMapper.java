package io.igorcossta.user;

import io.igorcossta.registration.UserRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "isEnabled", constant = "false")
    @Mapping(target = "role", expression = "java(io.igorcossta.user.Role.USER)")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(newUser.getPassword()))")
    public abstract User toEntity(UserRegistrationDTO newUser);
}
