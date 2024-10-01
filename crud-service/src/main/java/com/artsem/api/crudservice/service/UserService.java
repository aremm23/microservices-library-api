package com.artsem.api.crudservice.service;

import com.artsem.api.crudservice.UserDto;
import com.artsem.api.crudservice.filter.UserFilter;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface UserService {
    Page<UserDto> getList(UserFilter filter, Pageable pageable);

    UserDto getOne(Long id);

    List<UserDto> getMany(List<Long> ids);

    UserDto create(UserDto dto);

    UserDto patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    UserDto delete(Long id);

    void deleteMany(List<Long> ids);
}
