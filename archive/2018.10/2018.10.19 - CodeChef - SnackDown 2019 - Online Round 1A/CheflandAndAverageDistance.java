package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.fill;

public class CheflandAndAverageDistance {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[][] map = in.readTable(n, m);
        IntList[] rows = new IntList[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new IntArrayList();
        }
        int[] up = new int[2 * (n + m)];
        int[] down = new int[2 * (n + m)];
        int[] answer = new int[n + m - 2];
        for (int i = 0; i < m; i++) {
            fill(up, 0);
            for (int j = 0; j < n; j++) {
                for (int k : rows[j]) {
                    down[i - k + j]++;
                }
            }
            int upAt = n + m;
            int downAt = 0;
            for (int j = 0; j < n; j++) {
                if (map[j][i] == '1') {
                    for (int k = 0; k < answer.length; k++) {
                        answer[k] += up[upAt + k + 1] + down[downAt + k + 1];
                    }
                    down[downAt]++;
                    rows[j].add(i);
                }
                for (int k : rows[j]) {
                    down[downAt + i - k]--;
                    up[upAt + i - k]++;
                }
                upAt--;
                downAt++;
            }
        }
        out.printLine(answer);
    }
}
