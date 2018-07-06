package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static net.egork.misc.ArrayUtils.sumArray;

public class Printer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] need = in.readIntArray(4);
        int[] price = in.readIntArray(4);
        int bundle = in.readInt();
        long answer = 0;
        while (sumArray(need) != 0) {
            int min = MAX_VALUE;
            long total = 0;
            for (int i = 0; i < 4; i++) {
                if (need[i] != 0) {
                    min = Math.min(min, need[i]);
                    total += price[i];
                }
            }
            answer += min * Math.min(total, bundle);
            for (int i = 0; i < 4; i++) {
                if (need[i] != 0) {
                    need[i] -= min;
                }
            }
        }
        out.printLine(answer);
    }
}
