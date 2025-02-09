package com.mobeasy.api.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatterUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d MMMM 'à' HH'h'mm", Locale.FRENCH);

    public static String formatZonedDateTime(ZonedDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
