package com.xplorer.microservices.limits_service.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyTest {

    public static Date getCurrentDateInGMTMinus4() {
        // Get current time in America/New_York (handles DST properly)
        ZonedDateTime nowInTargetZone = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(nowInTargetZone);
        return Date.from(nowInTargetZone.toInstant());
    }

    public static void main(String[] args) {
        Date initialRunTime = getCurrentDateInGMTMinus4();
        System.out.println("Scheduler start time (stored): " + initialRunTime);
    }
}
