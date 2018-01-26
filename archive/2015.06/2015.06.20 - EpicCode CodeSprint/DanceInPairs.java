package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class DanceInPairs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int delta = in.readInt();
        int[] boys = IOUtils.readIntArray(in, count);
        int[] girls = IOUtils.readIntArray(in, count);
        Arrays.sort(boys);
        Arrays.sort(girls);
        int i = 0;
        int j = 0;
        int answer = 0;
        while (i < count && j < count) {
            if (Math.abs(boys[i] - girls[j]) <= delta) {
                answer++;
                i++;
                j++;
            } else if (boys[i] < girls[j]) {
                i++;
            } else {
                j++;
            }
        }
        out.printLine(answer);
    }
}
