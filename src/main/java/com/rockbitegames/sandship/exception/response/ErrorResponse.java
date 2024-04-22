package com.rockbitegames.sandship.exception.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@Builder(builderMethodName = "errorResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    OffsetDateTime timestamp = OffsetDateTime.now();
    int status;
    String error;
    String path;
    String message;


    public ErrorResponse(HttpStatus errorStatus, String path, String message) {
        this.error = errorStatus.name();
        this.status = errorStatus.value();
        this.path = path;
        this.message = message;
    }
}
