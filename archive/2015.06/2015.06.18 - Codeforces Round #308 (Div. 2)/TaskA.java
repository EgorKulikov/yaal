package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int answer = 0;
        for (int i = 0; i < count; i++) {
            int x0 = in.readInt();
            int y0 = in.readInt();
            int x1 = in.readInt();
            int y1 = in.readInt();
            answer += (x1 - x0 + 1) * (y1 - y0 + 1);
        }
        out.printLine(answer);
    }
}
