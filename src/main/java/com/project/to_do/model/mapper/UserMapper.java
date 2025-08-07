package com.project.to_do.model.mapper;

import com.project.to_do.model.dto.UserRequestDTO;
import com.project.to_do.model.dto.UserResponseDTO;
import com.project.to_do.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User fromRequestDtoToEntity(UserRequestDTO userRequestDTO);
    public UserRequestDTO fromEntityToRequestDto(User user);
    public UserResponseDTO fromEntityToResponseDto(User user);
}
