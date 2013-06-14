package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.lang.Math;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[][] matrix = IOUtils.readIntTable(in, 5, 5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == 1) {
                    out.printLine(Math.abs(i - 2) + Math.abs(j - 2));
                    return;
                }
            }
        }
    }
}
