package com.epam.training.ticketservice.presentation.cli.handler;

import static org.mockito.BDDMockito.given;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.exception.NoSuchAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

class AccountCommandHandlerTest {
    private static final String TEST_ADMIN = "admin";
    private static final String TEST_NOTADMIN = "not admin";

    @InjectMocks
    private static AccountCommandHandler underTest;

    @Mock
    private AccountLoginService accountLoginService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginPrivilegedShouldSuccessfullyLoggedIn() throws NoSuchAccountException {
        //Given
        String expected = "Alright. You are signed in.";
        given(accountLoginService.login(TEST_ADMIN, TEST_ADMIN)).willReturn(true);

        //When
        String actual = underTest.loginPrivileged(TEST_ADMIN, TEST_ADMIN);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().login(TEST_ADMIN, TEST_ADMIN);
    }

    @Test
    public void testLoginPrivilegedShouldUnSuccessfullyLoggedIn() throws NoSuchAccountException {
        //Given
        String expected = "Login failed due to incorrect credentials";
        given(accountLoginService.login(TEST_NOTADMIN, TEST_NOTADMIN)).willReturn(false);

        //When
        String actual = underTest.loginPrivileged(TEST_NOTADMIN, TEST_NOTADMIN);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().login(TEST_NOTADMIN, TEST_NOTADMIN);
    }

    @Test
    public void testSignOutPrivilegedShouldSignOut(){
        //Given
        String expected = "You are signed out.";

        //When
        String actual = underTest.signOutPrivileged();

        //Then
        then(accountLoginService).should().signOut();
        assertEquals(expected, actual);

    }

    @Test
    public void testDescribeAccount(){
        //Given
        String expected = "You are not signed in";
        given(accountLoginService.accountLoggedIn()).willReturn(false);

        //When
        String actual = underTest.describeAccount();

        //Then
        assertEquals(expected, actual);

    }

}