package net.egork;

import net.egork.generated.collections.queue.IntArrayQueue;
import net.egork.generated.collections.queue.IntQueue;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[][] map = readTable(in, n, n);
        int[][] qtyRow = new int[n][26];
        int[][] qtyCol = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != '?') {
                    qtyRow[i][map[i][j] - 'a']++;
                    qtyCol[j][map[i][j] - 'a']++;
                }
            }
        }
        IntQueue queue = new IntArrayQueue(2 * n);
        boolean[] done = new boolean[2 * n];
        for (int i = 0; i < n; i++) {
            char suitable = get(qtyRow[i]);
            if (suitable != 0) {
                queue.add(i);
                done[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            char suitable = get(qtyCol[i]);
            if (suitable != 0) {
                queue.add(i + n);
                done[i + n] = true;
            }
        }
        char[] type = new char[2 * n];
        int[] index = new int[2 * n];
        char[] symbol = new char[2 * n];
        for (int i = 2 * n - 1; i >= 0; i--) {
            int current = queue.poll();
            if (current < n) {
                type[i] = 'h';
                index[i] = current + 1;
                symbol[i] = get(qtyRow[current]);
                for (int j = 0; j < n; j++) {
                    if (!done[n + j] && map[current][j] != '?') {
                        qtyCol[j][map[current][j] - 'a']--;
                        if (get(qtyCol[j]) != 0) {
                            done[n + j] = true;
                            queue.add(n + j);
                        }
                    }
                }
            } else {
                current -= n;
                type[i] = 'v';
                index[i] = current + 1;
                symbol[i] = get(qtyCol[current]);
                for (int j = 0; j < n; j++) {
                    if (!done[j] && map[j][current] != '?') {
                        qtyRow[j][map[j][current] - 'a']--;
                        if (get(qtyRow[j]) != 0) {
                            done[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            out.printLine(type[i], index[i], symbol[i]);
        }
    }

    private char get(int[] array) {
        char nonZero = 1;
        for (int i = 0; i < 26; i++) {
            if (array[i] != 0) {
                if (nonZero != 1) {
                    return 0;
                }
                nonZero = (char) ('a' + i);
            }
        }
        return nonZero == 1 ? 'a' : nonZero;
    }
}
