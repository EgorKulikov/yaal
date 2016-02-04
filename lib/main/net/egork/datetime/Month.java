package net.egork.datetime;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public enum Month {
    NULL(null),
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");

    public final String longName;
    public final String shortName;

    private Month(String longName) {
        this.longName = longName;
        shortName = longName == null ? null : longName.substring(0, 3);
    }
}
