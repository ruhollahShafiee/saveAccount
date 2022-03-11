package com.kadmos.service.saveAccount.service;

import com.kadmos.service.saveAccount.data.Balance;
import com.kadmos.service.saveAccount.data.exception.NotFoundException;
import com.kadmos.service.saveAccount.persistence.entity.Account;
import com.kadmos.service.saveAccount.persistence.repository.AccountRepository;
import com.kadmos.service.saveAccount.service.impl.AccountServiceImpl;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AccountServiceTest {

    private static final String ACCOUNT_NUMBER = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
    private static final float AMOUNT = 45.2f;

    private static final Balance API_Balance = Balance.builder()
            .amount(AMOUNT)
            .build();


    private static final Account ACCOUNT = Account.builder()
            .accountNumber(ACCOUNT_NUMBER)
            .amount(AMOUNT)
            .build();

    private AccountRepository accountRepository;

    @RegisterExtension
    Mockery context = new JUnit5Mockery();

    @BeforeEach
    public void setUp() {
        accountRepository = context.mock(AccountRepository.class);
    }

    @Test
    public void getBalance() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(accountRepository).findByAccountNumber(ACCOUNT_NUMBER);
                will(returnValue(Optional.of(ACCOUNT)));
            }
        });

        AccountService accountService = new AccountServiceImpl(accountRepository);
        Balance balance = accountService.getBalance(ACCOUNT_NUMBER);
        assertThat(balance.getAmount(), is(equalTo(ACCOUNT.getAmount())));
    }

    @Test
    public void getAccountReturnsEmptyResults() {
        context.checking(new Expectations() {
            {
                oneOf(accountRepository).findByAccountNumber("test");
                will(returnValue(Optional.empty()));
            }
        });
        AccountService accountService = new AccountServiceImpl(accountRepository);
        Assertions.assertThrows(NotFoundException.class, () -> {
            accountService.getBalance("test");
        });
    }

    @Test
    public void updateBalance() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(accountRepository).findByAccountNumber(ACCOUNT_NUMBER);
                will(returnValue(Optional.of(ACCOUNT)));
                oneOf(accountRepository).save(with(any(Account.class)));
            }
        });

        AccountService accountService = new AccountServiceImpl(accountRepository);

        Assertions.assertDoesNotThrow(() -> {
            accountService.updateAccount(ACCOUNT_NUMBER, API_Balance);
        });
    }

    @Test
    public void updateAccountReturnsEmptyResults() {
        context.checking(new Expectations() {
            {
                oneOf(accountRepository).findByAccountNumber("test");
                will(returnValue(Optional.empty()));
            }
        });

        AccountService accountService = new AccountServiceImpl(accountRepository);
        Assertions.assertThrows(NotFoundException.class, () -> {
            accountService.updateAccount("test", API_Balance);
        });
    }
}
