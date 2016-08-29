package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readTable;

public class Collisions {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[][] matrix = readTable(in, n, m);
        int answer = 0;
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                cnt += matrix[j][i] - '0';
            }
            answer += cnt * (cnt - 1) / 2;
        }
        out.printLine(answer);
    }
}
