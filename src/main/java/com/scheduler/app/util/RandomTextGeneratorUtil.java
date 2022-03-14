package com.scheduler.app.util;

import org.apache.commons.text.RandomStringGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomTextGeneratorUtil {
    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(63, 64).build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57).build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomAlphabet(int length, boolean flag) {
        RandomStringGenerator pwdGenerator = null;
        if (flag) {
//			pwdGenerator = new RandomStringGenerator.Builder().withinRange(97, 122).build();
            pwdGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        } else {
            pwdGenerator = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();
        }
        return pwdGenerator.generate(length);
    }

    public String generateRandomCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 41).build();
        return pwdGenerator.generate(length);
    }

    public String generateCommonTextPassword() {
        String pwString = generateRandomSpecialCharacters(1).concat(generateRandomNumbers(2))
                .concat(generateRandomAlphabet(3, true))
                .concat(generateRandomAlphabet(2, false))
                .concat(generateRandomCharacters(2));
        List<Character> pwChars = pwString.chars()
                .mapToObj(data -> (char) data)
                .collect(Collectors.toList());
        Collections.shuffle(pwChars);
        return pwChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
