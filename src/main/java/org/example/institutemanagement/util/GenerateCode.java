package org.example.institutemanagement.util;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class GenerateCode {

    private static Integer currentCode = 1000;

    public static String generateSecurePassword() {
    CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        LCR.setNumberOfCharacters(2);

    CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        UCR.setNumberOfCharacters(2);

    CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        DR.setNumberOfCharacters(3);

    CharacterData specialChars = new CharacterData() {
        @Override
        public String getErrorCode() {
            return "ERROR_SPECIAL_CHAR";
        }

        @Override
        public String getCharacters() {
            return "@#$%&";
        }
    };

    CharacterRule SR = new CharacterRule(specialChars);
        SR.setNumberOfCharacters(1);

    PasswordGenerator passGen = new PasswordGenerator();

        return passGen.generatePassword(8, SR, LCR, UCR, DR);
        }
    public static synchronized String generateCode() {
        currentCode = currentCode + 1;
        return currentCode.toString();


    }
}
