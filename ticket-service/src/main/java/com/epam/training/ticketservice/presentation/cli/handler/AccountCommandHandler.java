package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.exception.NoSuchAccountException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountCommandHandler {

    private final AccountLoginService accountLoginService;

    public AccountCommandHandler(AccountLoginService accountLoginService) {
        this.accountLoginService = accountLoginService;
    }

    @ShellMethod(value = "Sign in privileged", key = "sign in privileged")
    public String loginPrivileged(final String name, final String password) {
        try {
            if (accountLoginService.login(name, password)) {
                return "Alright. You are signed in.";
            }
        } catch (NoSuchAccountException e) {
            e.printStackTrace();
        }
        return "Login failed due to incorrect credentials";
    }

    @ShellMethod(value = "Sign out privileged", key = "sign out")
    public String signOutPrivileged() {
        accountLoginService.signOut();
        return "You are signed out.";
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public String describeAccount() {
        if(accountLoginService.accountLoggedIn()){
            return "Signed in with privileged account '" + accountLoginService.getUsername()+"'";
        }
        return "You are not signed in";
    }
}
