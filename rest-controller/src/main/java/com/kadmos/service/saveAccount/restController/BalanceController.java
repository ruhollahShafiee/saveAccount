package com.kadmos.service.saveAccount.restController;


import com.kadmos.service.saveAccount.data.Balance;
import com.kadmos.service.saveAccount.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/balance",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Tag(name = "balance", description = "Operations pertaining to balance account")
public class BalanceController {

    private static final String JSON = MediaType.APPLICATION_JSON_UTF8_VALUE;
    final String accountNumber = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";

    private final AccountService accountService;

    @Operation(summary = "Retrieve balance amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all accounts"),
            @ApiResponse(responseCode = "401", description = "Authorization denied"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception")
    })
    @GetMapping(produces = JSON)
    public ResponseEntity getBalance() throws Exception {
        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }


    @Operation(summary = "update balance amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a customer"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Authorization denied"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "502", description = "An error has occurred with an upstream service")
    })
    @PutMapping
    public ResponseEntity updateBalance(@RequestBody Balance balance) {
        accountService.updateAccount(accountNumber, balance);
        return ResponseEntity.noContent().build();
    }


}
