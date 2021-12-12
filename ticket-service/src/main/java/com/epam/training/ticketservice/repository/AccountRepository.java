package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Account save(Account account);
}