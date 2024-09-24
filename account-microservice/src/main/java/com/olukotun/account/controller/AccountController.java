package com.olukotun.account.controller;

import com.olukotun.account.constant.AccountConstant;
import com.olukotun.account.dto.AccountContactInfoDto;
import com.olukotun.account.dto.CustomerDto;
import com.olukotun.account.dto.ErrorResponseDto;
import com.olukotun.account.dto.ResponseDto;
import com.olukotun.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Tag(
        name = "CRUD REST APIs for Account olukotun bank",
        description = "CRUD REST to create, update and retrieve "
)
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private final IAccountService accountService;
    private final Environment environment;
    private final AccountContactInfoDto accountContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Account REST API",
            description = "Create account rest api"

    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDTO, WebRequest requestEntity) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_200));
    }

    @Operation(
            summary = "Create Account REST API",
            description = "Create account rest api"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),

    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
                                                            String mobileNumber, RequestEntity request) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return new ResponseEntity<>(accountService.fetchAccount(mobileNumber), HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Create Account REST API",
            description = "Create account rest api"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed"
            )

    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Create Account REST API",
            description = "Create account rest api"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed"
            )

    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_DELETE));
        }
    }



    @Operation(
            summary = "Get build description",
            description = "Get build descriptions"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed"
            )

    })
    @GetMapping("/version")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }


    @Operation(
            summary = "Get build description",
            description = "Get build descriptions"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed"
            )

    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get build description",
            description = "Get build descriptions"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed"
            )

    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountContactInfoDto);
    }


}
