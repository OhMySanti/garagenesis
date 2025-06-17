package org.services;

import java.util.concurrent.atomic.AtomicLong;

public class IdCounter {
    static final AtomicLong idCounterService = new AtomicLong();

    public static Long getIdCounterService() {
        return idCounterService.get();
    }

    public static void setIdCounterService(Long value) {
        idCounterService.set(value);
    }
}
