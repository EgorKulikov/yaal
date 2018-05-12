package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class WaffleChoppers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int c = in.readInt();
        int h = in.readInt();
        int v = in.readInt();
        char[][] waffle = in.readTable(r, c);
        int total = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (waffle[i][j] == '@') {
                    total++;
                }
            }
        }
        if (total == 0) {
            out.printLine("Case #" + testNumber + ": POSSIBLE");
            return;
        }
        if (total % ((h + 1) * (v + 1)) != 0) {
            out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
            return;
        }
        int[] divsH = new int[h + 2];
        int per = total / (h + 1);
        int at = 1;
        int current = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (waffle[i][j] == '@') {
                    current++;
                }
            }
            if (current > per) {
                out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
                return;
            }
            if (current == per) {
                divsH[at++] = i + 1;
                current = 0;
            }
        }
        int[] divsV = new int[v + 2];
        per = total / (v + 1);
        at = 1;
        current = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (waffle[j][i] == '@') {
                    current++;
                }
            }
            if (current > per) {
                out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
                return;
            }
            if (current == per) {
                divsV[at++] = i + 1;
                current = 0;
            }
        }
        int part = total / (h + 1) / (v + 1);
        for (int i = 0; i < h + 1; i++) {
            for (int j = 0; j < v + 1; j++) {
                current = 0;
                for (int k = divsH[i]; k < divsH[i + 1]; k++) {
                    for (int l = divsV[j]; l < divsV[j + 1]; l++) {
                        if (waffle[k][l] == '@') {
                            current++;
                        }
                    }
                }
                if (current != part) {
                    out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
                    return;
                }
            }
        }
        out.printLine("Case #" + testNumber + ": POSSIBLE");
    }
}
