package com.kadmos.service.saveAccount.bdd.steps;

import com.kadmos.service.saveAccount.bdd.CucumberBootstrap;
import com.kadmos.service.saveAccount.data.Balance;
import com.kadmos.service.saveAccount.persistence.entity.Account;
import com.kadmos.service.saveAccount.persistence.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@Slf4j
public class AccountSteps extends CucumberBootstrap {

    @Autowired
    private AccountRepository accountRepository;

    private static final String ACCOUNT_NUMBER = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
    private static final float AMOUNT_VALUE = 45.2f;

    @After
    public void cleanUp() {
        log.info(">>> cleaning up after scenario!");
        accountRepository.deleteAll();
    }

    @AfterStep
    public void afterStep() {
        log.info(">>> AfterStep!");
        //placeholder for after step logic
    }

    @Before
    public void before() {
        log.info(">>> Before scenario!");
        //placeholder for before scenario logic
    }

    @BeforeStep
    public void beforeStep() {
        log.info(">>> BeforeStep!");
        //placeholder for before step logic
    }


    @Given("^the collection of accounts:$")
    public void collection_of_accounts(DataTable dataTable) {
        dataTable.asList(Account.class).forEach(account -> {
            saveBalance((Account)account);
        });
    }


    @When("^accountNumber (.+) is passed in to retrieve the balance$")
    public void get_account_value(String accountNumber) {
        ResponseEntity<Balance> response = testRestTemplate.getForEntity(
                "/balance", Balance.class);
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().getAmount(), is(equalTo(accountNumber)));
    }



    private void saveBalance(Account account) {
        accountRepository.save(Account.builder()
                .accountNumber(account.getAccountNumber())
                .amount(account.getAmount())
                .build());
    }
}
