package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.service.exception.NoSuchAccountException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountLoginServiceImplTest {
    private static final String TEST_USERNAME = "testusername";
    private static final String TEST_PASSWORD = "testpassword";
    private static final String TEST_ADMIN = "admin";

    private static AccountLoginServiceImpl underTest;

    @BeforeAll
    static void initTest(){
        underTest = new AccountLoginServiceImpl();
    }

    @Test
    public void testLoginShouldReturnFalseWhenNotAdmin() throws NoSuchAccountException {
        //Given in setup

        //When
        boolean actual = underTest.login(TEST_USERNAME, TEST_PASSWORD);

        //Then
        assertFalse(actual);
    }

    @Test
    public void testLoginShouldReturnTrueWhenAdmin() throws NoSuchAccountException {
        //Given in setup

        //When
        boolean actual = underTest.login(TEST_ADMIN, TEST_ADMIN);

        //Then
        assertTrue(actual);
    }
}