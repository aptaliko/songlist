package songlist.service;

import org.apache.commons.lang3.RandomStringUtils;
import songlist.model.features.rhythm.Rhythm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {

    public static Rhythm createRandomRhythm() {
        Rhythm rhythm = new Rhythm();
        rhythm.setId(randomUUID());
        rhythm.setName(randomString());
        rhythm.setMeter(randomString());

        return rhythm;
    }

    public static List<Rhythm> createRandomRhythms(int number) {
        List<Rhythm> rhythms = new ArrayList<>();
        for (int i = 0; i< number; i++) {
            rhythms.add(createRandomRhythm());
        }

        return rhythms;
    }

    public static String randomString() {
        return RandomStringUtils.random(10, true, false);
    }

    public static UUID randomUUID() {
        return UUID.randomUUID();
    }
}
