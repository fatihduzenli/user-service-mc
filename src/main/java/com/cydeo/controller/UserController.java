package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.dto.wrapper.ExceptionWrapper;
import com.cydeo.dto.wrapper.ResponseWrapper;
import com.cydeo.service.UserService;
import com.cydeo.util.SwaggerExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "UserController", description = "User controller endpoints")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed("Admin")
    @PostMapping("/create")
    @Operation(summary = "Create a user.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User is created.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "User already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_ALREADY_EXISTS_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "Role does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ROLE_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> createUser(@Valid @RequestBody UserDTO userDTO) {

        UserDTO createdUser = userService.create(userDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED)
                        .message("User is successfully created.")
                        .data(createdUser)
                        .build());

    }

    @RolesAllowed("Admin")
    @GetMapping("/read/{userName}")
    @Operation(summary = "Read a user by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is successfully retrieved.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_GET_RESPONSE_SINGLE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> getByUserName(@PathVariable("userName") String userName) {

        UserDTO foundUser = userService.readByUserName(userName);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("User is successfully retrieved.")
                        .data(foundUser)
                        .build());

    }

    @RolesAllowed("Admin")
    @GetMapping("/read/all")
    @Operation(summary = "Read all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users are successfully retrieved.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_GET_RESPONSE_LIST_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> getUsers() {

        List<UserDTO> foundUsers = userService.readAllUsers();

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Users are successfully retrieved.")
                        .data(foundUsers)
                        .build());

    }

    @RolesAllowed({"Admin", "Manager"})
    @GetMapping("/check/{username}")
    @Operation(summary = "Check if a user exists or not by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CHECK_RESPONSE_SINGLE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> checkByUserName(@PathVariable("username") String username) {

        boolean result = userService.checkByUserName(username);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("User exists.")
                        .data(result)
                        .build());

    }

    @RolesAllowed("Admin")
    @PutMapping("/update/{username}")
    @Operation(summary = "Update a user by username.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_UPDATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is successfully updated.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_UPDATE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "Role does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ROLE_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> updateUser(@PathVariable("username") String username, @Valid @RequestBody UserDTO userDTO) {

        UserDTO updatedUser = userService.update(username, userDTO);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("User is successfully updated.")
                        .data(updatedUser)
                        .build());

    }

    @RolesAllowed("Admin")
    @DeleteMapping("/delete/{userName}")
    @Operation(summary = "Update a user by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User is successfully deleted.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "User can not be deleted.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_DELETED_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Project count cannot be retrieved.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.PROJECT_COUNT_NOT_RETRIEVED_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Task count cannot be retrieved.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.TASK_COUNT_NOT_RETRIEVED_RESPONSE_EXAMPLE)))})
    public ResponseEntity<Void> deleteUser(@PathVariable("userName") String userName) {
        userService.delete(userName);
        return ResponseEntity.noContent().build();
    }

}
