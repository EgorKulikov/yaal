package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt() - 1;
        int year = -1;
//        k %= 66;
        for (int i = 1; i <= 12; i++) {
            int current = (i - 1) * (i - 2) / 2;
            if (k < current) {
                year = i;
                break;
            }
            k -= current;
        }
        if (year == -1) {
            year = 13 + k / 66;
            k %= 66;
        }
        int month = -1;
        int day = -1;
        for (int i = 1; ; i++) {
            if (k < i) {
                month = i + 1;
                day = k + 1;
                break;
            }
            k -= i;
        }
        out.printLine(year + "/" + month + "/" + day);
    }
}
