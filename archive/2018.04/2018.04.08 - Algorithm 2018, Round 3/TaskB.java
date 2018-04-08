package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        long answer = 0;
        int last = 0;
        for (int i = 1; i < n; i++) {
            int b = a[i];
            if (b == 0 && a[i - 1] > 0) {
                out.printLine(-1);
                return;
            }
            int current = 0;
            while (last > 0 && a[i - 1] <= 1000000) {
                a[i - 1] *= 2;
                last--;
            }
            while (b < a[i - 1]) {
                b *= 2;
                current++;
                answer++;
            }
            current += last;
            answer += last;
            last = current;
        }
        out.printLine(answer);
    }
}
