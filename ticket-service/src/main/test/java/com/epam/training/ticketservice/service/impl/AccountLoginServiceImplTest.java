package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.exception.NoSuchAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AccountLoginServiceImplTest {

    @Mock
    private final AccountLoginService accountLoginService = new AccountLoginServiceImpl();

    @Test
    void testLoginServiceShouldLoggedInAdminAccountWhenUserLoggingIn() throws NoSuchAccountException {
        // Given
        String name = "admin";
        String pwd = "admin";
        String pwd2 = "a";

        // When
        boolean vissza = accountLoginService.login(name, pwd);
        boolean vissza2 = accountLoginService.login(name, pwd2);

        // Then
        Assertions.assertTrue(vissza, String.valueOf(true));
        Assertions.assertFalse(vissza2, String.valueOf(false));
    }

    @Test
    void testGetUsernameShouldGetUsernameWhenTypeUserName() {
        // Given
        String user = "pelda";
        String user2 = "p";

        // When
        accountLoginService.setUsername(user);

        // Then
        Assertions.assertEquals(accountLoginService.getUsername(), user);
        Assertions.assertNotEquals(accountLoginService.getUsername(), user2);
    }

}