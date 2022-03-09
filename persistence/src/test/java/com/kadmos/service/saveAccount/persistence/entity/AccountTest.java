package com.kadmos.service.saveAccount.persistence.entity;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private static final String ACCOUNT_NUMBER = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
    private static final float AMOUNT = 45.2f;


    @Test
    public void objectCreation() {
        Account object = Account.builder()
                .accountNumber(ACCOUNT_NUMBER)
                .amount(AMOUNT)
                .build();
        MatcherAssert.assertThat(object.getAccountNumber(), Matchers.is(Matchers.sameInstance(ACCOUNT_NUMBER)));
        MatcherAssert.assertThat(object.getAmount(), Matchers.is(Matchers.sameInstance(AMOUNT)));
    }
}
