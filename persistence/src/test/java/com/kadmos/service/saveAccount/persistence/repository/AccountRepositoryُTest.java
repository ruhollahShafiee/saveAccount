package com.kadmos.service.saveAccount.persistence.repository;

import com.kadmos.service.saveAccount.persistence.entity.Account;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DataJpaTest
public class AccountRepositoryُTest {

    private static final String ACCOUNT_NUMBER = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
    private static final float AMOUNT = 45.2f;


    private static final Account ACCOUNT = Account.builder()
            .accountNumber(ACCOUNT_NUMBER)
            .amount(AMOUNT)
            .build();

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void cleanup() {
        accountRepository.deleteAll();
    }

    @Test
    public void findByAccountNumber() {
        accountRepository.save(ACCOUNT);
        Optional<Account> newAccount = accountRepository.findByAccountNumber(ACCOUNT_NUMBER);
        MatcherAssert.assertThat(newAccount.isPresent(), Matchers.is(Matchers.equalTo(true)));
        MatcherAssert.assertThat(newAccount.get(), Matchers.is(Matchers.equalTo(ACCOUNT)));
    }

    @Test
    public void findByCustomerIdReturnsNoResults() {
        Optional<Account> newCustomer = accountRepository.findByAccountNumber("differentId");
        MatcherAssert.assertThat(newCustomer.isPresent(), Matchers.is(Matchers.equalTo(false)));
    }
}
