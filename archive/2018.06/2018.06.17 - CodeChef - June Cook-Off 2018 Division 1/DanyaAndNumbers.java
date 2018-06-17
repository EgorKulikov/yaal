package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DanyaAndNumbers {

    public static final int BUBEN = 9;
    public static final int MASK = (1 << BUBEN) - 1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        in.readInt();
        int[] a = in.readIntArray(n);
        int[] qtyBig = new int[1 << BUBEN];
        int[][] qty = new int[1 << BUBEN][1 << BUBEN];
        for (int i : a) {
            int top = i >> BUBEN;
            for (int j = top; j != 0; j = (j - 1) & top) {
                qtyBig[j]++;
            }
            int bottom = i & MASK;
            for (int j = bottom; j != 0; j = (j - 1) & bottom) {
                qty[top][j]++;
            }
        }
        for (int x = 0; x < m; x++) {
            int type = in.readInt();
            int arg = in.readInt();
            if (type != 3) {
                int i = arg;
                int add = type == 1 ? 1 : -1;
                int top = i >> BUBEN;
                for (int j = top; j != 0; j = (j - 1) & top) {
                    qtyBig[j] += add;
                }
                int bottom = i & MASK;
                for (int j = bottom; j != 0; j = (j - 1) & bottom) {
                    qty[top][j] += add;
                }
            } else {
                int answerTop = 0;
                for (int j = BUBEN - 1; j >= 0; j--) {
                    if (qtyBig[answerTop + (1 << j)] >= arg) {
                        answerTop += 1 << j;
                    }
                }
                int answerBottom = 0;
                for (int j = BUBEN - 1; j >= 0; j--) {
                    int nAnswer = answerBottom + (1 << j);
                    int other = MASK - answerTop;
                    int total = qty[answerTop][nAnswer];
                    for (int k = other; k != 0; k = (k - 1) & other) {
                        total += qty[k + answerTop][nAnswer];
                    }
                    if (total >= arg) {
                        answerBottom = nAnswer;
                    }
                }
                out.printLine((answerTop << BUBEN) + answerBottom);
            }
        }
    }
}
