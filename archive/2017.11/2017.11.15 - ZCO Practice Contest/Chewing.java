package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.InputReader.readIntArray;
import static net.egork.misc.ArrayUtils.sort;

public class Chewing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] hardness = in.readIntArray(n);
        sort(hardness);
        long answer = 0;
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            while (j > i && hardness[i] + hardness[j] >= k) {
                j--;
            }
            answer += max(0, j - i);
        }
        out.printLine(answer);
    }
}
