package com.cydeo.dto;

import com.cydeo.enums.Gender;
import com.cydeo.util.RoleDTODeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
public class UserDTO {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Firstname is required.")
    @Size(min = 3, max = 16, message = "Firstname length should be min 3, max 16.")
    private String firstName;

    @NotBlank(message = "Lastname is required.")
    @Size(min = 3, max = 16, message = "Lastname length should be min 3, max 16.")
    private String lastName;

    @NotBlank(message = "Username is required.")
    @Email(message = "Username should be in valid email format.")
    @Size(min = 3, max = 16, message = "Username length should be min 3, max 16.")
    private String userName;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}",
            message = "The password must be 4 characters or more, with at least 1 capital letter, 1 small letter, and 1 digit.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must include 10 digits.")
    private String phone;

    private boolean enabled;

    @NotNull(message = "Role is required.")
    @JsonDeserialize(using = RoleDTODeserializer.class)
    private RoleDTO role;

    @NotNull(message = "Gender is required.")
    private Gender gender;

}
