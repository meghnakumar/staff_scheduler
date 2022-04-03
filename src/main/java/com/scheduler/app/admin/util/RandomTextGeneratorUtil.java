package com.scheduler.app.admin.util;

import org.apache.commons.text.RandomStringGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class RandomTextGeneratorUtil {
    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(RANDOM_VALUES.SIXTY_THREE.getNumVal(), RANDOM_VALUES.SIXTY_THREE.getNumVal()).build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(RANDOM_VALUES.FORTY_EIGHT.getNumVal(), RANDOM_VALUES.FIFTY_SEVEN.getNumVal()).build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomAlphabet(int length, boolean flag) {
        RandomStringGenerator pwdGenerator = null;
        if (flag) {
            pwdGenerator = new RandomStringGenerator.Builder().withinRange(RANDOM_VALUES.SMALL_A.getCharVal(), RANDOM_VALUES.SMALL_Z.getCharVal()).build();
        } else {
            pwdGenerator = new RandomStringGenerator.Builder().withinRange(RANDOM_VALUES.CAP_A.getCharVal(), RANDOM_VALUES.CAP_Z.getCharVal()).build();
        }
        return pwdGenerator.generate(length);
    }

    public String generateRandomCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(RANDOM_VALUES.THIRTY_SEVEN.getNumVal(), RANDOM_VALUES.FORTY_ONE.getNumVal()).build();
        return pwdGenerator.generate(length);
    }

    public String generateCommonTextPassword() {
        String pwString = generateRandomSpecialCharacters(RANDOM_VALUES.ONE.getNumVal()).concat(generateRandomNumbers(RANDOM_VALUES.TWO.getNumVal()))
                .concat(generateRandomAlphabet(RANDOM_VALUES.THREE.getNumVal(), true))
                .concat(generateRandomAlphabet(RANDOM_VALUES.TWO.getNumVal(), false))
                .concat(generateRandomCharacters(RANDOM_VALUES.TWO.getNumVal()));
        List<Character> pwChars = pwString.chars()
                .mapToObj(data -> (char) data)
                .collect(Collectors.toList());
        Collections.shuffle(pwChars);
        return pwChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public enum RANDOM_VALUES {
        SIXTY_FOUR(64), SIXTY_THREE(63), FORTY_EIGHT(48),
        FIFTY_SEVEN(57) ,CAP_A('A'),SMALL_A('a'),CAP_Z('Z'),
        SMALL_Z('z')
        ,ONE(1),TWO(2),THREE(3),THIRTY_SEVEN(37),
        FORTY_ONE(41);
        private int numVal;
        private char charVal;
        RANDOM_VALUES(int numVal) {
            this.numVal = numVal;
        }
        RANDOM_VALUES(char charVal){
            this.charVal = charVal;
        }
        public int getNumVal() {
            return numVal;
        }
        public char getCharVal(){
            return charVal;
        }
    }
}
