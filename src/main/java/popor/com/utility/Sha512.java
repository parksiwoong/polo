package popor.com.utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Sha512 {
    private Sha512() {
    }

    public static String hash(String input) {
        return hash(input, null);
    }

    private static String hash(String input, String fallback){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();
            messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
            return String.format("%0128x", new BigInteger(1, messageDigest.digest()));
        } catch (Exception ignored) {
            return fallback;
        }
    }
}

