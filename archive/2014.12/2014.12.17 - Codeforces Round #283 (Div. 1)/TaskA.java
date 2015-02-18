package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        char[][] table = IOUtils.readTable(in, rowCount, columnCount);
        int[] base = new int[rowCount];
        int answer = 0;
        for (int i = 0; i < columnCount; i++) {
            boolean good = true;
            for (int j = 1; j < rowCount; j++) {
                if (table[j][i] < table[j - 1][i] && base[j] == base[j - 1]) {
                    good = false;
                    break;
                }
            }
            if (!good) {
                answer++;
                continue;
            }
            for (int j = 1; j < rowCount; j++) {
                if (table[j][i] != table[j - 1][i]) {
                    base[j] = j;
                }
                base[j] = Math.max(base[j], base[j - 1]);
            }
        }
        out.printLine(answer);
    }
}
