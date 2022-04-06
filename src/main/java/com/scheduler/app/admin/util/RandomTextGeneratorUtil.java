package com.scheduler.app.admin.util;

import org.apache.commons.text.RandomStringGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type - Random Text Generator Utility Class
 * This class is responsible for generating randomised sequence of characters which is utilised as a One Time Password
 * for a newly created employee through the Employee Creation Service.
 */
public class RandomTextGeneratorUtil {


    /**
     * The enum Random values.
     */
    public enum ASCII_VALUES_MAP {

        // ASCII value for '@'
        SIXTY_FOUR(64),

        // ASCII value for '?'
        SIXTY_THREE(63),

        // ASCII value for '0'
        FORTY_EIGHT(48),

        // ASCII value for '9'
        FIFTY_SEVEN(57) ,

        // Capital 'A' letter
        CAP_A('A'),

        // Small 'a' letter
        SMALL_A('a'),

        // Capital 'Z' letter
        CAP_Z('Z'),

        // Small 'z'
        SMALL_Z('z'),

        // Number 1
        ONE(1),

        // Number 2
        TWO(2),

        // Number 3
        THREE(3),

        // ASCII value for '%'
        THIRTY_SEVEN(37),

        // ASCII value for ')'
        FORTY_ONE(41);

        private int numVal;
        private char charVal;

        ASCII_VALUES_MAP(int numVal) {
            this.numVal = numVal;
        }
        ASCII_VALUES_MAP(char charVal){
            this.charVal = charVal;
        }

        public int getNumVal() {
            return numVal;
        }
        public char getCharVal(){
            return charVal;
        }
    }



    /**
     * Generates a string of Random Special Characters.
     *
     * @param length the expected length of the output string.
     * @return the string of randomised special character.
     */
    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(ASCII_VALUES_MAP.SIXTY_THREE.getNumVal(), ASCII_VALUES_MAP.SIXTY_FOUR.getNumVal()).build();
        return pwdGenerator.generate(length);
    }

    /**
     * Generates a string of  random numbers.
     *
     * @param length the expected length of the output string.
     * @return the string of randomised numbers.
     */
    public String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(ASCII_VALUES_MAP.FORTY_EIGHT.getNumVal(), ASCII_VALUES_MAP.FIFTY_SEVEN.getNumVal()).build();
        return pwdGenerator.generate(length);
    }

    /**
     * Generates a string of randomised alphabets.
     *
     * @param length the expected length of the output string.
     * @param flag   the flag for upper or lower case
     * @return the string output
     */
    public String generateRandomAlphabet(int length, boolean flag) {
        RandomStringGenerator pwdGenerator = null;
        if (flag) {
            pwdGenerator = new RandomStringGenerator.Builder().withinRange(ASCII_VALUES_MAP.SMALL_A.getCharVal(), ASCII_VALUES_MAP.SMALL_Z.getCharVal()).build();
        } else {
            pwdGenerator = new RandomStringGenerator.Builder().withinRange(ASCII_VALUES_MAP.CAP_A.getCharVal(), ASCII_VALUES_MAP.CAP_Z.getCharVal()).build();
        }
        return pwdGenerator.generate(length);
    }

    /**
     * Generates a string of randomised characters.
     *
     * @param length the expected length of the output string.
     * @return the string output.
     */
    public String generateRandomCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(ASCII_VALUES_MAP.THIRTY_SEVEN.getNumVal(), ASCII_VALUES_MAP.FORTY_ONE.getNumVal()).build();
        return pwdGenerator.generate(length);
    }

    /**
     * Generates the password by using the randomised strings obtained.
     *
     * @return the generated password string.
     */
    public String generateCommonTextPassword() {
        String pwString = generateRandomSpecialCharacters(ASCII_VALUES_MAP.ONE.getNumVal()).concat(generateRandomNumbers(ASCII_VALUES_MAP.TWO.getNumVal()))
                .concat(generateRandomAlphabet(ASCII_VALUES_MAP.THREE.getNumVal(), true))
                .concat(generateRandomAlphabet(ASCII_VALUES_MAP.TWO.getNumVal(), false))
                .concat(generateRandomCharacters(ASCII_VALUES_MAP.TWO.getNumVal()));
        List<Character> pwChars = pwString.chars()
                .mapToObj(data -> (char) data)
                .collect(Collectors.toList());
        Collections.shuffle(pwChars);
        return pwChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
