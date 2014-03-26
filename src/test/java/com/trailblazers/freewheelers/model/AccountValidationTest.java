package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.trailblazers.freewheelers.model.AccountValidation.verifyInputs;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountValidationTest {

    public static final String SOME_EMAIL = "guenter.grass@gmail.com";
    public static final String SOME_PASSWORD = "V3ry Secure!";
    public static final String SOME_NAME = "Günter Grass";
    public static final String SOME_PHONE = "945542741";
    private Account account;
    public static final String SOME_COUNTRY="Germany";
    public static final long SOME_COUNTRY_ID=3;


    @Before
    public void setup() {
        account = new Account()
                .setEmail_address(SOME_EMAIL)
                .setPassword(SOME_PASSWORD)
                .setAccount_name(SOME_NAME)
                .setPhoneNumber(SOME_PHONE)
                .setEnabled(true)
                .setCountry_id(SOME_COUNTRY_ID);

    }
    @Test
    public void shouldHaveNoErrorsForValidInput() throws Exception {
        HashMap errors = verifyInputs(account);

        assertThat(errors.size(), is(0));
    }
    
    @Test
    public void shouldComplainAboutAnInvalidEmail() throws Exception {
        String invalidEmail = "invalid.email.address";

        account.setEmail_address(invalidEmail);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("email", "enter a valid email", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPassword() throws Exception {
        String emptyPassword = "";

        account.setPassword(emptyPassword);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("password", "enter a password", errors);
    }

    @Test
    public void shouldComplainIfPasswordIsInvalid() throws Exception {
        String password = "hello";

        account.setPassword(password);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("validPassword", "enter a valid password", errors);

    }

    @Test
    public void shouldComplainAboutAnEmptyName() throws Exception {
        String emptyName = "";

        account.setAccount_name(emptyName);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("name", "enter a name", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPhoneNumber() throws Exception {
        String emptyPhoneNumber = "";

        account.setPhoneNumber(emptyPhoneNumber);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", "Phone number should contain only digits and must be nine digits long", errors);
    }

    @Test
    public void shouldComplainIfThePhoneNumberContainsNonDigitCharacters() throws Exception {
        String phoneNumber = "123$45678";

        account.setPhoneNumber(phoneNumber);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", "Phone number should contain only digits and must be nine digits long", errors);
    }

    @Test
    public void shouldComplainIfThereAreNotNineDigits() throws Exception {
        String invalidPhoneNumber = "11";

        account.setPhoneNumber(invalidPhoneNumber);
        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", "Phone number should contain only digits and must be nine digits long", errors);
    }

    @Test
    public void shouldComplainAboutZeroForCountryId() throws Exception {
        long country = 0;

        account.setCountry_id(country);

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("country", "enter a country name", errors);
    }

    private void assertThereIsOneErrorFor(String field, String expected, HashMap<String, String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(field), containsString(expected));
    }
}
