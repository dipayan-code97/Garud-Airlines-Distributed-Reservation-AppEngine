package githubProjectRepo.dipayan-code97.bankingservice.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dipayan Paul
 * @created : 8/6/2023, Sunday
 **/
public class PasswordValidatorTest {

    @Test
    void validPasswords() {
        assertTrue(PasswordValidator.isValid("ab12!@BB"));
        assertTrue(PasswordValidator.isValid("A1q!{}90()"));
        assertTrue(PasswordValidator.isValid("This1s4Passw0rd!"));
    }

    @Test
    void invalidPasswords() {
        assertFalse(PasswordValidator.isValid(""));
        assertFalse(PasswordValidator.isValid("aB1!()"));
        assertFalse(PasswordValidator.isValid("GGHH[]{}"));
        assertFalse(PasswordValidator.isValid("        "));
    }
}