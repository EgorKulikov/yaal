package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndSubmatrix {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int[][] matrix = IOUtils.readIntTable(in, size, size);
        int[][] xors = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                xors[i][j] = xors[i - 1][j] ^ xors[i][j - 1] ^ xors[i - 1][j - 1] ^ matrix[i - 1][j - 1];
            }
        }
        int answer = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                for (int k = 0; k < i; k++) {
                    for (int l = 0; l < j; l++) {
                        answer = Math.max(answer, xors[i][j] ^ xors[i][l] ^ xors[k][j] ^ xors[k][l]);
                    }
                }
            }
        }
        out.printLine(answer);
    }
}
