package ru.otus.timofeev.task16.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import ru.otus.timofeev.task16.dto.ErrorDto;
import ru.otus.timofeev.task16.dto.NewUserDto;
import ru.otus.timofeev.task16.dto.UserDto;
import ru.otus.timofeev.task16.model.User;
import ru.otus.timofeev.task16.service.UserMapper;
import ru.otus.timofeev.task16.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Tag(
        name = "User registration API"
)
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creating user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED",
                    content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "username, password or email is null or email is in wrong format",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    }),
            @ApiResponse(
            responseCode = "409",
            description = "username or email is already exists",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))})}
    )
    public UserDto create(@Valid @RequestBody NewUserDto userDto) {
        log.info("Creation request. UserDto : {}", userDto);
        User user = mapper.toEntity(userDto);

        User savedUser = service.create(user);

        return mapper.toDto(savedUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Getting user by ID")
    public UserDto getUser( @Parameter(
            required = true,
            description = "ID of the user") @PathVariable Long id) {
        log.info("Getting user with ID {} request", id);
        User user = service.getById(id);
        return mapper.toDto(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Getting all users")
    public List<UserDto> getUsers() {
        log.info("Getting all users request");
        List<User> users = service.getUsers();
        return mapper.toDto(users);
    }
}


