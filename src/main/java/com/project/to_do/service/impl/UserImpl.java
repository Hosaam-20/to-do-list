package com.project.to_do.service.impl;


import com.project.to_do.exception.UserNotFoundException;
import com.project.to_do.model.dto.ApiResponse;
import com.project.to_do.model.dto.UserRequestDTO;
import com.project.to_do.model.dto.UserResponseDTO;
import com.project.to_do.model.entity.User;
import com.project.to_do.model.mapper.TaskMapper;
import com.project.to_do.model.mapper.UserMapper;
import com.project.to_do.repository.UserRepository;
import com.project.to_do.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    @Override
//    public ApiResponse getAllUsers(int pageNo, int pageSize) {
//        return null;
//    }
//
//    @Override
//    public UserResponseDTO getUserById(Long id) {
//        return null;
//    }
//
//    @Override
//    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
//        return null;
//    }
//
//    @Override
//    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id) {
//        return null;
//    }
//
//    @Override
//    public UserResponseDTO deleteUser(Long id) {
//        return null;
//    }
//    private final PasswordEncoder passwordEncoder;
//
    @Override
    public ApiResponse getAllUsers(int pageNo, int pageSize){

        log.info("get all users");
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> userPage = this.userRepository.findAll(pageable);
        List<User> entities = userPage.getContent();
        List<UserResponseDTO> dtoList = entities.stream()
                .map(el ->this.userMapper.fromEntityToResponseDto(el))
                .collect(Collectors.toList());

        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setContent(dtoList.stream().toList());
        apiResponse.setPageNo(userPage.getNumber());
        apiResponse.setPageSize(userPage.getSize());
        apiResponse.setTotalElements(userPage.getTotalElements());
        apiResponse.setTotalPages(userPage.getTotalPages());
        apiResponse.setIsLast(userPage.isLast());


        return apiResponse;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        log.info("get user by id: {}", id);
        User isExist = this.userRepository.findById(id).orElseThrow(
                () ->  new UserNotFoundException("user not found")
        );

        return this.userMapper.fromEntityToResponseDto(isExist);
    }


    @Override @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        log.info("create new user: {}", userRequestDTO);
        User user = this.userMapper.fromRequestDtoToEntity(userRequestDTO);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepository.save(user);

        return this.userMapper.fromEntityToResponseDto(user);
    }

    @Override @Transactional
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id) {

        log.info("update user info {}", userRequestDTO);
        User isExist = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        isExist.setUsername(userRequestDTO.getUsername());
        isExist.setEmail(userRequestDTO.getEmail());
        isExist.setPassword(userRequestDTO.getPassword());
//        isExist.setPassword(passwordEncoder.encode(isExist.getPassword()));
        this.userRepository.save(isExist);

        return this.userMapper.fromEntityToResponseDto(isExist);

    }

    @Override @Transactional
    public UserResponseDTO deleteUser(Long id) {

        log.info("delete user by id: {}", id);
        User isExist = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        return this.userMapper.fromEntityToResponseDto(isExist);

    }



}
