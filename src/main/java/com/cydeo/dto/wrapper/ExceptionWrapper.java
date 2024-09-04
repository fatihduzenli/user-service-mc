package com.cydeo.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionWrapper {

    private boolean success;
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    private Integer errorCount;
    private List<ValidationExceptionWrapper> validationExceptions;

}
