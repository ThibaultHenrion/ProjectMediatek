package com.anicetti.mediatek.services.auth;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {
    private static final int SIZE = 128;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateNewToken() {
        byte[] randomBytes = new byte[SIZE];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
