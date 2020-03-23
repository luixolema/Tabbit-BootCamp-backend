package com.tabit.dcm2.testutils;

import java.util.Random;

public class ValueProvider {
    private Random random = new Random();

    public String randomString(String base) {
        int letterSmallA = 97; // letter 'a'
        int letterSmallZ = 122; // letter 'z'
        int targetStringLength = 10;

        String generatedString = random.ints(letterSmallA, letterSmallZ + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public long randomId() {
        return random.nextLong();
    }

    public boolean randomBoolean() {
        return random.nextBoolean();
    }
}
