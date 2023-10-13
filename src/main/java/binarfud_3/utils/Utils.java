package binarfud_3.utils;

import java.util.Random;
import java.util.stream.IntStream;

public class Utils {
    public static final String SEPARATOR = "===========================";

    public static final String TABLE = "\t|\t";

    private static final Random random = new Random();

    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();

        IntStream.range(0, 10)
                .mapToObj(i -> {
                    int index = random.nextInt(characters.length());
                    return characters.charAt(index);
                })
                .forEach(randomString::append);

        return randomString.toString();
    }
}