package com.project.to_do.service;

import com.project.to_do.model.dto.ApiResponse;
import com.project.to_do.model.dto.UserRequestDTO;
import com.project.to_do.model.dto.UserResponseDTO;


public interface UserService {

    public ApiResponse getAllUsers(int pageNo, int pageSize);
    public UserResponseDTO getUserById(Long id);
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id);
    public UserResponseDTO deleteUser(Long id);
}
