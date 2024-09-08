package com.olukotun.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(
        name="Reponse",
        description = "Schema to hold successful response"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(
            name="status scode",example = "200"
    )
    private String statusCode;
    @Schema(
            name="status message to represent successful response"
    )
    private String statusMessage;
}
