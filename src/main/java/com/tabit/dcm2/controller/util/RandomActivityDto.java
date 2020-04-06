package com.tabit.dcm2.controller.util;

import com.google.common.base.Joiner;
import com.tabit.dcm2.entity.Activity;
import com.tabit.dcm2.service.dto.ActivityDTO;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class RandomActivityDto {

    static ActivityDTO createRandomActivityDTO() {
        Random random = new Random();

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId((long) random.nextInt());
        activityDTO.setDate(randomLocalDate());
        activityDTO.setPos(randomString("base"));
        activityDTO.setPrice(random.nextDouble());
        activityDTO.setType(Activity.ActivityType.TYPE_1);

        return activityDTO;
    }

    private static LocalDate randomLocalDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    private static String randomString(String base) {
        Random random = new Random();

        int letterSmallA = 97;
        int letterSmallZ = 122;
        int targetStringLength = 10;

        return Joiner.on("").join(base, random.ints(letterSmallA, letterSmallZ + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
    }
}
