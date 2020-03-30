package com.tabit.dcm2.testutils;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ValueProvider {
    private Random random = new Random();

    public String randomString(String base) {
        int letterSmallA = 97; // letter 'a'
        int letterSmallZ = 122; // letter 'z'
        int targetStringLength = 10;

        return random.ints(letterSmallA, letterSmallZ + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public long randomId() {
        return random.nextLong();
    }


    public Integer randomInt() {
        return random.nextInt();
    }

    public boolean randomBoolean() {
        return random.nextBoolean();
    }

    public LocalDate randomLocalDate() {

        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
