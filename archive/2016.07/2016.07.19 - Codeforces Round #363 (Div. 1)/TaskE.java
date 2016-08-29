package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int s = in.readInt();
        int m = in.readInt();
        int h = in.readInt();
        int day = in.readInt() - 1;
        int date = in.readInt() - 1;
        int month = in.readInt() - 1;
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            long t = in.readLong() + 1;
            if (s != -1) {
                long secs = t % 60;
                if (secs > s) {
                    t += s - secs + 60;
                } else {
                    t += s - secs;
                }
            }
            if (m != -1) {
                long mins = (t / 60) % 60;
                if (mins != m && s == -1) {
                    t -= t % 60;
                }
                if (mins > m) {
                    t += (m - mins + 60) * 60;
                } else {
                    t += (m - mins) * 60;
                }
            }
            if (h != -1) {
                long hours = (t / 60 / 60) % 24;
                if (hours != h) {
                    if (s == -1) {
                        t -= t % 60;
                    }
                    if (m == -1) {
                        t -= ((t / 60) % 60) * 60;
                    }
                }
                if (hours > h) {
                    t += (h - hours + 24) * 60 * 60;
                } else {
                    t += (h - hours) * 60 * 60;
                }
            }
            long fullDay = 24 * 60 * 60;
            long daysSince = t / fullDay;
            long add = t % fullDay;
            long weekDay = (daysSince + 3) % 7;
            daysSince += 90 * 366 + 280 * 365;
            long cycled = daysSince % (97 * 366 + 303 * 365);
            int cy;
            for (int j = 0; ; j++) {
                int cYear = 365;
                if (isLeap(j)) {
                    cYear = 366;
                }
                if (cycled < cYear) {
                    cy = j;
                    break;
                } else {
                    cycled -= cYear;
                }
            }
            int cm;
            for (int j = 0; ; j++) {
                int cMonth = DAYS_IN_MONTH[j];
                if (j == 1 && isLeap(cy)) {
                    cMonth = 29;
                }
                if (cycled < cMonth) {
                    cm = j;
                    break;
                } else {
                    cycled -= cMonth;
                }
            }
            int cd = (int) cycled;
            long currentDate = daysSince;
            while (true) {
                boolean dayOk;
                if (day == -2) {
                    if (date == -2) {
                        dayOk = true;
                    } else {
                        dayOk = cd == date;
                    }
                } else {
                    if (date == -2) {
                        dayOk = weekDay == day;
                    } else {
                        dayOk = weekDay == day || cd == date;
                    }
                }
                boolean monthOk = month == -2 || cm == month;
                if (dayOk && monthOk) {
                    break;
                }
                currentDate++;
                weekDay++;
                if (weekDay == 7) {
                    weekDay = 0;
                }
                cd++;
                int cMonth = DAYS_IN_MONTH[cm];
                if (cm == 1 && isLeap(cy)) {
                    cMonth = 29;
                }
                if (cd == cMonth) {
                    cd = 0;
                    cm++;
                }
                if (cm == 12) {
                    cm = 0;
                    cy++;
                }
            }
            if (currentDate != daysSince) {
                currentDate -= 90 * 366 + 280 * 365;
                if (s == -1) {
                    add -= add % 60;
                }
                if (m == -1) {
                    add -= (add / 60 % 60) * 60;
                }
                if (h == -1) {
                    add -= (add / (60 * 60) % 24) * (60 * 60);
                }
                t = currentDate * fullDay + add;
            }
            out.printLine(t);
        }
    }

    protected boolean isLeap(int j) {
        return j % 400 == 0 || j % 4 == 0 && j % 100 != 0;
    }
}
