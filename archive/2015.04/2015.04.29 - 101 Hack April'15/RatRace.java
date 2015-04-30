package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RatRace {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] speed = IOUtils.readIntArray(in, count);
        int[] distance = IOUtils.readIntArray(in, count);
        int answer = 0;
        for (int i = 1; i < count; i++) {
            if (speed[answer] * distance[i] < speed[i] * distance[answer]) {
                answer = i;
            }
        }
        for (int i = 0; i < count; i++) {
            if (speed[answer] * distance[i] == speed[i] * distance[answer]) {
                out.printLine(i + 1);
            }
        }
    }
}
