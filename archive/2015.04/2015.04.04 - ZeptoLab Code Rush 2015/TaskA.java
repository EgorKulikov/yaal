package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char[] plan = IOUtils.readCharArray(in, count);
        for (int i = 0; i < count; i++) {
            if (plan[i] == '.') {
                continue;
            }
            for (int j = 1; i + 4 * j < count; j++) {
                boolean good = true;
                for (int k = 1; k <= 4; k++) {
                    if (plan[i + k * j] == '.') {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    out.printLine("yes");
                    return;
                }
            }
        }
        out.printLine("no");
    }
}
