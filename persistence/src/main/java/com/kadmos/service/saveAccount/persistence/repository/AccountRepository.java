package com.kadmos.service.saveAccount.persistence.repository;

import com.kadmos.service.saveAccount.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByAccountNumber(String accountNumber);

}
