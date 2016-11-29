package fr.agez.planterra.data.util;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Adrien Agez
 */

public class DateFormatter {


    // #################### SINGLETON ###############################
    private static final DateFormatter INSTANCE = new DateFormatter();

    private DateFormatter() {}

    public static DateFormatter get() {
        return INSTANCE;
    }
    // ##############################################################

    private final DateTimeFormatter format = DateTimeFormat.forPattern("E dd MMMM");

    public String format(LocalDate date) {
        return date.toString(format);
    }

}
