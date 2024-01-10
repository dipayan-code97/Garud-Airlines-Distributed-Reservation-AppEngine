package githubProjectRepo.dipayan-code97.bankingservice.utility;

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/
public class PasswordValidator {

    private static final String UPPERCASE_VALID_PERMUTANT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_VALID_PERMUTANT = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIAL_CASE_VALID_PERMUTANT = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
    private static final String NUMERIC_DIGIT_CASE_VALID_PERMUTANT = "0123456789";

    public PasswordValidator() {
    }

    public static Boolean isValid(String userEnteredPassword) {

        if (userEnteredPassword.length() < 8) {
            return false;
        }
        Boolean upperCase = false;
        Boolean lowerCase = false;
        Boolean numericDigitCase = false;
        Boolean specialCase = false;
        Boolean illegalCase = false;

        for (int password = 0; password < userEnteredPassword.length(); password++) {
            char currentPassword = userEnteredPassword.charAt(password);
            if (UPPERCASE_VALID_PERMUTANT.contains(String.valueOf(currentPassword))) {
                upperCase = true;
                continue;
            } else if (LOWERCASE_VALID_PERMUTANT.contains(String.valueOf(currentPassword))) {
                lowerCase = true;
                continue;
            } else if (Character.isDigit(currentPassword)) {
                numericDigitCase = true;
                continue;
            } else if (SPECIAL_CASE_VALID_PERMUTANT.contains(String.valueOf(currentPassword))) {
                specialCase = true;
                continue;
            } else {
                illegalCase = true;
            }
        }
        return ((upperCase && lowerCase) && (numericDigitCase && specialCase));
    }

    @Override
    public String toString() {
        return ("PasswordValidator{}");
    }
}
