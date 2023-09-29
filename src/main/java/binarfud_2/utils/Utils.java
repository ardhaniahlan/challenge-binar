package binarfud_2.utils;

import java.util.Random;

public class Utils {
    public static final String SEPARATOR = "===========================";

    public static final String TABLE = "\t|\t";

    private static final Random random = new Random();

    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}