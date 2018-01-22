package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class PartitionTheNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int n = in.readInt();
        char[] answer = new char[n];
        int sum = 0;
        for (int i = n; i > 10; i--) {
            if (i == x) {
                answer[i - 1] = '2';
            } else if (sum >= 0) {
                answer[i - 1] = '0';
                sum -= i;
            } else {
                answer[i - 1] = '1';
                sum += i;
            }
        }
        int limit = n < 10 ? (1 << n) : 1024;
        for (int i = 0; i < limit; i++) {
            int current = sum;
            for (int j = 0; j < n && j < 10; j++) {
                if (j + 1 == x) {
                    continue;
                }
                if ((i >> j & 1) == 0) {
                    current -= j + 1;
                } else {
                    current += j + 1;
                }
            }
            if (current == 0) {
                for (int j = 0; j < n && j < 10; j++) {
                    answer[j] = (char) ((i >> j & 1) + '0');
                }
                answer[x - 1] = '2';
                out.printLine(answer);
                return;
            }
        }
        out.printLine("impossible");
    }
}
