package net.egork.datetime;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class DateTimeUtils {
    public static Time timePassed(Time from, Time to) {
        int hours = to.getHours() - from.getHours();
        int minutes = to.getMinutes() - from.getMinutes();
        int seconds = to.getSeconds() - from.getSeconds();
        if (seconds < 0) {
            seconds += 60;
            minutes--;
        }
        if (minutes < 0) {
            minutes += 60;
            hours--;
        }
        if (hours < 0) {
            hours += 24;
        }
        return new Time(hours, minutes, seconds);
    }
}
