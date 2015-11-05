package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SaveQuantumland {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] b = IOUtils.readIntArray(in, n);
        int answer = 0;
        for (int i = 1; i < n; i++) {
            if (b[i] == 0 && b[i - 1] == 0) {
                answer++;
                i++;
            }
        }
        out.printLine(answer);
    }
}
