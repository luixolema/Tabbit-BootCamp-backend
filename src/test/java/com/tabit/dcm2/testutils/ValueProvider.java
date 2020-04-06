package com.tabit.dcm2.testutils;

import com.google.common.base.Joiner;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ValueProvider {
    private Random random = new Random();

    public String randomString(String base) {
        int letterSmallA = 97;
        int letterSmallZ = 122;
        int targetStringLength = 10;

        return Joiner.on("").join(base, random.ints(letterSmallA, letterSmallZ + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
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

    public Double randomDouble() {
        return random.nextDouble();
    }
}
