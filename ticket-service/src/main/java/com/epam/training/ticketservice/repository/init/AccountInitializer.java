package com.epam.training.ticketservice.repository.init;

import com.epam.training.ticketservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("! prod")
public class AccountInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInitializer.class);

    private AccountRepository accountRepository;

    public AccountInitializer(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
