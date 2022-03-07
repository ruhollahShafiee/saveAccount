package com.kadmos.service.saveAccount.service.impl;

import com.kadmos.service.saveAccount.data.Balance;
import com.kadmos.service.saveAccount.data.exception.NotFoundException;
import com.kadmos.service.saveAccount.persistence.entity.Account;
import com.kadmos.service.saveAccount.persistence.repository.AccountRepository;
import com.kadmos.service.saveAccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    //Time-to-live for messages published.
    private static final String MESSAGE_TTL_IN_SECONDS = "1000";

    //The topic for order service
    private static final String TOPIC_ORDER_SERVICE = "order-service";

    //The name of the pubsub
    private static final String PUBSUB_NAME = "customer-order-integration";

    @Override
    public Balance getBalance(String accountNumber) throws Exception{

        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NotFoundException("Could not find customer with account number: " + accountNumber));
        return new Balance(account.getAmount());

    }

    @Override
    public void updateAccount(String accountNumber, Balance balacne) {

        Account account=accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NotFoundException("Could not find customer with account number: " + accountNumber));
        account.setAmount(balacne.getAmount());
        accountRepository.save(account);


    }


}
