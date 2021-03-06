package com.kadmos.service.saveAccount.restController;

import com.kadmos.service.saveAccount.data.Balance;
import com.kadmos.service.saveAccount.service.AccountService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BalanceControllerTest {

    private static final String ACCOUNT_NUMBER = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
    private static final float AMOUNT = 45.3f;

    private static final Balance BALANCE = Balance.builder()
            .amount(AMOUNT)
            .build();
    private UriComponentsBuilder uriBuilder;

    private AccountService accountService;

    @RegisterExtension
    public Mockery context = new JUnit5Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @BeforeEach
    public void setUp() {
        uriBuilder = context.mock(UriComponentsBuilder.class);
        accountService = context.mock(AccountService.class);
    }

    @Test
    public void getBalance() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(accountService).getBalance(ACCOUNT_NUMBER);
                will(returnValue(BALANCE));
            }
        });

        BalanceController balanceController = new BalanceController(accountService);
        ResponseEntity responseEntity = balanceController.getBalance();
        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(responseEntity.getBody(), is(sameInstance(BALANCE)));
    }

    @Test
    public void updateAccount() throws Exception {
        Balance balance = Balance.builder()
                .amount(AMOUNT)
                .build();

        context.checking(new Expectations() {
            {
                oneOf(accountService).updateAccount(ACCOUNT_NUMBER, balance);
            }
        });

        BalanceController balanceController = new BalanceController(accountService);
        ResponseEntity responseEntity = balanceController.updateBalance(balance);
        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.NO_CONTENT)));
        assertThat(responseEntity.getBody(), is(nullValue()));
    }
}