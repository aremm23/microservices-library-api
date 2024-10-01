package com.artsem.api.crudservice;

import com.artsem.api.crudservice.model.UserDetails;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDetails toEntity(UserDto userDto);

    UserDto toDto(UserDetails user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDetails partialUpdate(UserDto userDto, @MappingTarget UserDetails user);

    UserDetails updateWithNull(UserDto userDto, @MappingTarget UserDetails user);
}