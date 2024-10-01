package com.artsem.api.crudservice.service;

import com.artsem.api.crudservice.UserDto;
import com.artsem.api.crudservice.UserMapper;
import com.artsem.api.crudservice.filter.UserFilter;
import com.artsem.api.crudservice.model.UserDetails;
import com.artsem.api.crudservice.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<UserDto> getList(UserFilter filter, Pageable pageable) {
        Specification<UserDetails> spec = filter.toSpecification();
        Page<UserDetails> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::toDto);
    }

    @Override
    public UserDto getOne(Long id) {
        Optional<UserDetails> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @Override
    public List<UserDto> getMany(List<Long> ids) {
        List<UserDetails> users = userRepository.findAllById(ids);
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto create(UserDto dto) {
        UserDetails user = userMapper.toEntity(dto);
        UserDetails resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    @Override
    public UserDto patch(Long id, JsonNode patchNode) throws IOException {
        UserDetails user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        UserDto userDto = userMapper.toDto(user);
        objectMapper.readerForUpdating(userDto).readValue(patchNode);
        userMapper.updateWithNull(userDto, user);

        UserDetails resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<UserDetails> users = userRepository.findAllById(ids);

        for (UserDetails user : users) {
            UserDto userDto = userMapper.toDto(user);
            objectMapper.readerForUpdating(userDto).readValue(patchNode);
            userMapper.updateWithNull(userDto, user);
        }

        List<UserDetails> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(UserDetails::getId)
                .toList();
    }

    @Override
    public UserDto delete(Long id) {
        UserDetails user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.toDto(user);
    }

    @Override
    public void deleteMany(List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
}
