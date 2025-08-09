package com.xplorer.microservices.limits_service.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyTest {

    public static void main(String[] args) {
        Date currentDate = new Date();

        // Convert current UTC instant to America/New_York time
        LocalDateTime localDateTimeInGmt4 = currentDate.toInstant()
                .atZone(ZoneId.of("America/New_York"))
                .toLocalDateTime()
                .withSecond(0)
                .withNano(0);
        System.out.println("localDateTimeInGmt4: "+localDateTimeInGmt4);

        // Convert that back to an Instant and then Date
        Date endTime = Date.from(localDateTimeInGmt4
                .atZone(ZoneId.of("America/New_York"))
                .toInstant());
        System.out.println("endTime: "+endTime);

        ZonedDateTime gmt4DateTime = endTime
                .toInstant()
                .atZone(ZoneId.of("America/New_York"));
        System.out.println("gmt4DateTime: "+gmt4DateTime);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = gmt4DateTime.format(formatter);
        System.out.println("API-compatible GMT-4 date string: " + formatted);
    }
}
