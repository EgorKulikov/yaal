package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.concatenate;

public class KitchenTimetable {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = concatenate(new int[]{0}, readIntArray(in, n));
        int[] b = readIntArray(in, n);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (a[i + 1] - a[i] >= b[i]) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
