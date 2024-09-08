package com.olukotun.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
     name="Customer",
     description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(

            description = "Name of the customer", example = "olukotun"
    )
    @NotEmpty(message="Name can not be nul or empty")
    @Size(min = 5, max = 30, message = "Length of customer name should be between 5 and 30")
    private String name;

    @Schema(

            description = "Mobile Number", example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(

            description = "Name of the customer", example = "olukotuno@gmail.com"
    )
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email should be valid")
    private String email;
    @Schema(

            description = "Account information"
    )
    private AccountsDto accountsDto;
}
