package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class CTableSplitting1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[][] map = new char[n + 2][m + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = in.readCharacter();
            }
        }
        int answer = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int edges = 0;
                if (map[i][j] != map[i + 1][j]) {
                    edges++;
                }
                if (map[i][j] != map[i][j + 1]) {
                    edges++;
                }
                if (map[i + 1][j + 1] != map[i + 1][j]) {
                    edges++;
                }
                if (map[i + 1][j + 1] != map[i][j + 1]) {
                    edges++;
                }
                answer += edges & 1;
            }
        }
        out.printLine(answer / 2);
    }
}
