package com.anicetti.mediatek.services.auth;

import java.util.*;

public class TokenRuntimeRegistry {
    private static Map<String, Boolean> tokens = new HashMap<String, Boolean>();
    private final static int REVOKE_TIME_MINUTES = 120;

    public static synchronized void addToken(String token) {
        tokens.put(token, true);

        TimerTask remove_task = new TimerTask() {
            @Override
            public void run() {
                tokens.remove(token);
            }
        };

        Timer timer = new Timer("Timer");

        long delay = 1000L * 60 * REVOKE_TIME_MINUTES;
        timer.schedule(remove_task, delay);
    }

    public static synchronized boolean isValid(String token) {
        return tokens.get(token);
    }
}
