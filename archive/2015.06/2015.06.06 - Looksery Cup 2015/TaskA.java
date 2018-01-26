package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        char[][] image = IOUtils.readTable(in, rowCount, columnCount);
        char[] current = new char[4];
        char[] sample = "acef".toCharArray();
        int answer = 0;
        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < columnCount - 1; j++) {
                current[0] = image[i][j];
                current[1] = image[i][j + 1];
                current[2] = image[i + 1][j];
                current[3] = image[i + 1][j + 1];
                Arrays.sort(current);
                if (Arrays.equals(current, sample)) {
                    answer++;
                }
            }
        }
        out.printLine(answer);
    }
}
