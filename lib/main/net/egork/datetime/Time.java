package net.egork.datetime;

import net.egork.numbers.IntegerUtils;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final int seconds;

    public static Time parse(String time, String pattern) {
        int length = time.length();
        if (length != pattern.length()) {
            throw new IllegalArgumentException();
        }
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(pattern.charAt(i)) == 'H') {
                hours *= 10;
                hours += time.charAt(i) - '0';
            } else if (Character.toUpperCase(pattern.charAt(i)) == 'M') {
                minutes *= 10;
                minutes += time.charAt(i) - '0';
            } else if (Character.toUpperCase(pattern.charAt(i)) == 'S') {
                seconds *= 10;
                seconds += time.charAt(i) - '0';
            }
        }
        return new Time(hours, minutes, seconds);
    }

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isValid() {
        return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60;
    }

    @Override
    public String toString() {
        return toString("HH:MM:SS");
    }

    public String toString(String pattern) {
        int copyHours = hours;
        int copyMinutes = minutes;
        int copySeconds = seconds;
        StringBuilder result = new StringBuilder();
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char character = pattern.charAt(i);
            switch (character) {
            case 'S':
            case 's':
                result.append(copySeconds % 10);
                copySeconds /= 10;
                break;
            case 'M':
            case 'm':
                result.append(copyMinutes % 10);
                copyMinutes /= 10;
                break;
            case 'H':
            case 'h':
                result.append(copyHours % 10);
                copyHours /= 10;
                break;
            default:
                result.append(character);
            }
        }
        return result.reverse().toString();
    }

    public int totalMinutes() {
        return hours * 60 + minutes;
    }

    public Time normalize() {
        if (isValid()) {
            return this;
        }
        int copySeconds = seconds;
        int copyMinutes = minutes;
        int copyHours = hours;
        copyMinutes += IntegerUtils.trueDivide(copySeconds, 60);
        copySeconds = IntegerUtils.trueMod(copySeconds, 60);
        copyHours += IntegerUtils.trueDivide(copyMinutes, 60);
        copyMinutes = IntegerUtils.trueMod(copyMinutes, 60);
        copyHours = IntegerUtils.trueMod(copyHours, 24);
        return new Time(copyHours, copyMinutes, copySeconds);
    }

    public int totalSeconds() {
        return totalMinutes() * 60 + seconds;
    }

    public int compareTo(Time o) {
        return totalSeconds() - o.totalSeconds();
    }

    public Time advance(int seconds) {
        int total = totalSeconds() + seconds;
        return new Time(total / (60 * 60), total / 60 % 60, total % 60);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Time time = (Time) o;

        if (hours != time.hours) {
            return false;
        }
        if (minutes != time.minutes) {
            return false;
        }
        if (seconds != time.seconds) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = hours;
        result = 31 * result + minutes;
        result = 31 * result + seconds;
        return result;
    }
}
