package com.example.springmovie.mappers;

import com.example.springmovie.dto.UserDisplayDto;
import com.example.springmovie.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserDisplayDto userDisplayDto);

    UserDisplayDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDisplayDto userDisplayDto, @MappingTarget User user);


    List<UserDisplayDto> toDto(List<User> user);
}