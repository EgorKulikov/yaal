package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TwistyTuple {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, count);
        long answer = 0;
        for (int i = 0; i < count; i++) {
            int more = 0;
            for (int j = i + 1; j < count; j++) {
                if (array[j] > array[i]) {
                    more++;
                } else if (array[j] < array[i]) {
                    answer += more;
                }
            }
        }
        out.printLine(answer);
    }
}
