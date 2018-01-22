package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readIntArray;
import static net.egork.misc.ArrayUtils.sort;

public class Variation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] numbers = in.readIntArray(n);
        sort(numbers);
        long answer = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && numbers[j] - numbers[i] < k) {
                j++;
            }
            answer += n - j;
        }
        out.printLine(answer);
    }
}
