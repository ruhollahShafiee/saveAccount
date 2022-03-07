package com.kadmos.service.saveAccount.service;

import com.kadmos.service.saveAccount.data.Balance;

public interface AccountService {

    Balance getBalance(String accountNumber) throws Exception;

    void updateAccount(String accountNumber,Balance balance);

}
