package net.egork.datetime;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Date implements Comparable<Date> {
    private static final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final String[] WEEKDAYS =
            {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public final int year;
    public final int month;
    public final int day;
    private int weekday;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        weekday = -1;
    }

    public int getWeekday() {
        if (weekday == -1) {
            weekday = asInt();
            weekday += 6;
            weekday %= 7;
        }
        return weekday;
    }

    public int asInt() {
        int day = (year - 1) * 365;
        day += (year - 1) / 4;
        day -= (year - 1) / 100;
        day += (year - 1) / 400;
        for (int i = 1; i < month; i++) {
            day += DAYS_IN_MONTH[i];
        }
        day += this.day;
        if (isLeapYear(year) && month > 2) {
            day++;
        }
        return day;
    }

    public boolean isValid() {
        return !(month > 12 || month < 1) && !(day > DAYS_IN_MONTH[month] &&
                (day != 29 || month != 2 || !isLeapYear(year))) && day >= 1;
    }

    public Date next() {
        int year = this.year;
        int month = this.month;
        int day = this.day;
        day++;
        Date next = new Date(year, month, day);
        if (!next.isValid()) {
            next = new Date(year, month + 1, 1);
        }
        if (!next.isValid()) {
            next = new Date(year + 1, 1, 1);
        }
        if (weekday != -1) {
            next.weekday = (weekday + 1) % 7;
        }
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Date date = (Date) o;

        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }

    public static Date parse(String date, String pattern) {
        int length = date.length();
        if (length != pattern.length()) {
            throw new IllegalArgumentException();
        }
        int year = 0;
        int month = 0;
        int day = 0;
        for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(pattern.charAt(i)) == 'Y') {
                year *= 10;
                year += date.charAt(i) - '0';
            } else if (Character.toUpperCase(pattern.charAt(i)) == 'M') {
                month *= 10;
                month += date.charAt(i) - '0';
            } else if (Character.toUpperCase(pattern.charAt(i)) == 'D') {
                day *= 10;
                day += date.charAt(i) - '0';
            }
        }
        return new Date(year, month, day);
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public int compareTo(Date o) {
        return asInt() - o.asInt();
    }

    @Override
    public String toString() {
        return toString("DD.MM.YYYY");
    }

    public String toString(String pattern) {
        int copyYear = year;
        int copyMonth = month;
        int copyDay = day;
        StringBuilder result = new StringBuilder();
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char character = pattern.charAt(i);
            switch (character) {
            case 'D':
            case 'd':
                result.append(copyDay % 10);
                copyDay /= 10;
                break;
            case 'M':
            case 'm':
                result.append(copyMonth % 10);
                copyMonth /= 10;
                break;
            case 'Y':
            case 'y':
                result.append(copyYear % 10);
                copyYear /= 10;
                break;
            default:
                result.append(character);
            }
        }
        return result.reverse().toString();
    }
}
