package org.party.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.party.Model.LoginResModel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService();
    }

    @Test
    void testLogin() throws Exception {
        // Setup
        // Run the test
        final LoginResModel result = userServiceUnderTest.login("loginCode");

        // Verify the results
    }

    @Test
    void testLogin_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> userServiceUnderTest.login("loginCode")).isInstanceOf(Exception.class);
    }

    @Test
    void testCheckLogin() throws Exception {
        assertThat(userServiceUnderTest.checkLogin("openId", "session_key")).isEqualTo("result");
        assertThatThrownBy(() -> userServiceUnderTest.checkLogin("openId", "session_key"))
                .isInstanceOf(IOException.class);
        assertThatThrownBy(() -> userServiceUnderTest.checkLogin("openId", "session_key"))
                .isInstanceOf(InterruptedException.class);
    }

    @Test
    void testGetAccessToken() throws Exception {
        String a = "123456";
        System.out.printf(a.substring(0,4) + "-" + a.substring(4));
    }

    @Test
    void testGetHmacSHA256() throws Exception {
        System.out.printf(userServiceUnderTest.getHmacSHA256(new String(), "LXNk181Aba7ECV8EaTVW0Q=="));
    }
}
