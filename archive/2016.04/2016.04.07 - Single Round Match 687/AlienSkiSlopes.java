package net.egork;

import java.util.Arrays;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class AlienSkiSlopes {
    int[] down;
    boolean[][] edge;
    boolean[] was;
    private int[][] difference;
    int[] max;

    public int[] raise(int[] h) {
        int n = (int) Math.round(Math.sqrt(h.length));
        difference = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                difference[i][j] = h[i * n + j];
            }
        }
        int[] result = new int[n];
        down = new int[n];
        was = new boolean[n];
        max = new int[n];
        while (true) {
            prepare();
            boolean good = true;
            for (int i = 0; i < n; i++) {
                Arrays.fill(was, false);
                if (!dfs(i)) {
                    good = false;
                    break;
                }
            }
            if (good) {
                break;
            }
            int delta = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (was[i]) {
                    for (int j = 0; j < n; j++) {
                        if (down[j] == -1 || !was[down[j]]) {
                            delta = Math.min(delta, max[j] - difference[j][i]);
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (was[i]) {
                    result[i] -= delta;
                    for (int j = 0; j < n; j++) {
                        difference[j][i] += delta;
                    }
                }
            }
        }
        int min = minElement(result);
        for (int i = 0; i < n; i++) {
            result[i] -= min;
        }
        return result;
    }

    private boolean dfs(int vertex) {
        if (was[vertex]) {
            return false;
        }
        was[vertex] = true;
        for (int i = 0; i < down.length; i++) {
            if (max[i] == difference[i][vertex] && (down[i] == -1 || dfs(down[i]))) {
                down[i] = vertex;
                return true;
            }
        }
        return false;
    }

    private void prepare() {
        Arrays.fill(down, -1);
        for (int i = 0; i < down.length; i++) {
            max[i] = 0;
            for (int j = 0; j < down.length; j++) {
                max[i] = Math.max(max[i], difference[i][j]);
            }
        }
    }
}
