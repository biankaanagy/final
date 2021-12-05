package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.exception.NoSuchAccountException;
import com.epam.training.ticketservice.service.impl.AccountLoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountCommandHandler {

    private final AccountLoginService accountLoginService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountLoginServiceImpl.class);

    public AccountCommandHandler(AccountLoginService accountLoginService) {
        this.accountLoginService = accountLoginService;
    }

    @ShellMethod(value = "Sign in privileged", key = "sign in privileged")
    public void loginPriv(final String name, final String pwd) {

        try {
            if (accountLoginService.login(name, pwd)) {
                LOGGER.info("Alright. You are signed in.");
                //return "Alright. You are signed in.";
            } else {
                LOGGER.info("Login failed due to incorrect credentials");
                //return "Login failed due to incorrect credentials";
            }
        } catch (NoSuchAccountException e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Sign out privileged", key = "sign out")
    public void signOutPriv() {
        accountLoginService.signOut();
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public void describeAccount() {
        String user = accountLoginService.getUsername();
        if (user.isEmpty()) {
            LOGGER.info("You are not signed in");
            //return "You are not signed in";
        }
        LOGGER.info("Signed in with privileged account "+user);
        //return "Signed in with privileged account " + user;
    }
}
