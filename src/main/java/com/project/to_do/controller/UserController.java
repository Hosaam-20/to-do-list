package com.project.to_do.controller;

import com.project.to_do.model.dto.ApiResponse;
import com.project.to_do.model.dto.UserRequestDTO;
import com.project.to_do.model.dto.UserResponseDTO;
import com.project.to_do.service.impl.UserImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserImpl userImpl;

    @GetMapping(path = "/")
    public ResponseEntity<ApiResponse> getAllUseres(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
                                                    @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(this.userImpl.getAllUsers(pageNo, pageSize));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id")  Long id){
        return ResponseEntity.ok(this.userImpl.getUserById(id));
    }

    @PostMapping(path = "/")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){

        UserResponseDTO user = this.userImpl.createUser(userRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUserId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(user);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO userRequestDTO,
                                      @PathVariable("id") Long id){
        return ResponseEntity.ok(this.userImpl.updateUser(userRequestDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userImpl.deleteUser(id));
    }
}
