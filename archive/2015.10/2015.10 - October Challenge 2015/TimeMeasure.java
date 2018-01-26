package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TimeMeasure {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double a = in.readDouble();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                int hour = i * 60 + j;
                int minute = j * 12;
                int delta = Math.abs(hour - minute);
                if (delta > 360) {
                    delta = 720 - delta;
                }
                if (Math.abs(delta / 2d - a) < 1d / 120) {
                    out.printFormat("%02d:%02d\n", i, j);
                }
            }
        }
    }
}
