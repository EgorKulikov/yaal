package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.ArrayUtils.fillColumn;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        if (n == 0) {
            throw new UnknownError();
        }
        char[] source = in.readCharArray(n);
        char[] destination = in.readCharArray(n);
        int[][] answer = new int[n][1 << n];
        fill(answer, MAX_VALUE);
        fillColumn(answer, 0, 0);
        int required = 0;
        for (int i = 0; i < n; i++) {
            if (source[i] != destination[i]) {
                required += 1 << i;
            }
        }
        for (int i = 0; i < (1 << n); i++) {
            if ((i & required) != i) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (answer[j][i] == MAX_VALUE) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (source[k] != destination[k] && ((i >> k & 1) == 0)) {
                        for (int l = 0; l < n; l++) {
                            if (source[l] != destination[l] && source[l] != source[k] && ((i >> l & 1) == 0)) {
                                answer[l][i + (1 << k) + (1 << l)] = Math.min(answer[l][i + (1 << k) + (1 << l)],
                                        answer[j][i] + abs(j - k) + abs(k - l));
                            }
                        }
                    }
                }
            }
        }
        int result = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(result, answer[i][required]);
        }
        out.printLine(result);
    }
}
