package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PlayOff {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] answer = new int[1 << count];
        int current = 0;
        for (int i = 0; i <= count; i++) {
            for (int j = 0; j < (1 << count); j += (1 << (count - i))) {
                if (answer[j] == 0)
                    answer[j] = ++current;
            }
        }
        out.printLine(answer);
    }
}
