package com.olukotun.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

@Schema(
        name="Accounts",
        description = "Schema to hold account information"
)
public class AccountsDto {

    @Schema(

            description = "Account number of customer"
    )
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;
    @Schema(

            description = "AccountType of customer ", example = "Savings"
    )
    @NotEmpty(message = "AccountType can not be a null or empty")
    private String  accountType;
    @Schema(

            description = "Branch address of bank "
    )
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;
}
