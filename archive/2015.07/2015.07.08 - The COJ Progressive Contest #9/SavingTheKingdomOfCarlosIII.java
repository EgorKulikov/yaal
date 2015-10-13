package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SavingTheKingdomOfCarlosIII {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long[][] value = new long[4][count];
        for (int i = 0; i < 4; i++) {
            int at = in.readInt() - 1;
            value[0][at] = in.readLong();
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4 - i; j++) {
                value[i][j] = value[i - 1][j + 1] - value[i - 1][j];
            }
        }
        for (int i = 4; i < count; i++) {
            value[3][i - 3] = value[3][i - 4];
            for (int j = 2; j >= 0; j--) {
                value[j][i - j] = value[j][i - j - 1] + value[j + 1][i - j - 1];
            }
        }
        out.printLine(value[0][count - 1]);
    }
}
