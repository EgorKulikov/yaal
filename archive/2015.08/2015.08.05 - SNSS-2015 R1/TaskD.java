package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        int n = in.readInt();
        int m = in.readInt();
        char[][] image = IOUtils.readTable(in, n, m);
        int required = k * (k + 1) / 2;
        int[][][] color = new int[26][n + 1][m + 1];
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= m; l++) {
                    color[i][j][l] = color[i][j - 1][l] + color[i][j][l - 1] - color[i][j - 1][l - 1] + (image[j - 1][l - 1] == 'a' + i ? 1 : 0);
                }
            }
        }
        for (int height = 1; height <= required; height++) {
            if (required % height != 0) {
                continue;
            }
            int width = required / height;
            for (int i = 0; i + height <= n; i++) {
                for (int j = 0; j + width <= m; j++) {
                    int mask = 0;
                    for (int l = 0; l < 26; l++) {
                        int qty = color[l][i][j] + color[l][i + height][j + width] - color[l][i][j + width] - color[l][i + height][j];
                        if (qty <= k && qty != 0) {
                            mask |= 1 << (qty - 1);
                        }
                    }
                    if (mask == (1 << k) - 1) {
                        out.printLine(i + 1, j + 1);
                        out.printLine(i + height, j + width);
                        return;
                    }
                }
            }
        }
    }
}
