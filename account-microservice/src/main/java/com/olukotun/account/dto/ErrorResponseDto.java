package com.olukotun.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
@Schema(
        name="Errror",
        description = "Schema to hold error response"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            description = "Api invoked by client"
    )
    private String apiPath;
    @Schema(
            description = "Error cod representing what happened"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error message representing What happened"
    )
    private String errorMessage;
    @Schema(
            description = "Time representing when error occur"
    )
    private LocalDateTime errorTimestamp;
}
