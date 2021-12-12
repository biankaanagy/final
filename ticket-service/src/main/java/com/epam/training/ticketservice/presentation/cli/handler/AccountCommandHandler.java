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

    public AccountCommandHandler(AccountLoginService accountLoginService) {
        this.accountLoginService = accountLoginService;
    }

    @ShellMethod(value = "Sign in privileged", key = "sign in privileged")
    public String loginPriv(final String name, final String password) {
        try {
            if (accountLoginService.login(name, password)) {
                return "Alright. You are signed in.";
            } else {
                return "Login failed due to incorrect credentials";
            }
        } catch (NoSuchAccountException e) {
            e.printStackTrace();
        }
        return "Login failed due to incorrect credentials";
    }

    @ShellMethod(value = "Sign out privileged", key = "sign out")
    public String signOutPriv() {
        accountLoginService.signOut();
        return "You are signed out.";
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public String describeAccount() {
        if(accountLoginService.accountLoggedIn()){
            return "Signed in with privileged account " + accountLoginService.getUsername();
        }
        return "You are not signed in";
    }
}
