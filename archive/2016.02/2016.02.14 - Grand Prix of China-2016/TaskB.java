package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        readIntArrays(in, a, b);
        decreaseByOne(a, b);
        int[] graph = new int[n];
        for (int i = 0; i < m; i++) {
            graph[a[i]] |= 1 << b[i];
            graph[b[i]] |= 1 << a[i];
        }
        int processed = 0;
        long answer = 1;
        long[] current = new long[1 << 15];
        long[] next = new long[1 << 15];
        int[] vertices = new int[30];
        int[] cGraph = new int[30];
        int[] cover = new int[30];
        for (int i = 0; i < n; i++) {
            if ((processed >> i & 1) == 1) {
                continue;
            }
            int left = 1 << i;
            int right = 0;
            while (true) {
                int wasLeft = left;
                int wasRight = right;
                for (int j = 0; j < n; j++) {
                    if ((left >> j & 1) == 1) {
                        right |= graph[j];
                    }
                    if ((right >> j & 1) == 1) {
                        left |= graph[j];
                    }
                }
                if (wasLeft == left && wasRight == right) {
                    break;
                }
            }
            if (Integer.bitCount(left) > Integer.bitCount(right)) {
                int temp = left;
                left = right;
                right = temp;
            }
            int[] id = new int[n];
            int nextLeft = 0;
            int nextRight = 0;
            for (int j = 0; j < n; j++) {
                if ((left >> j & 1) == 1) {
                    cover[nextLeft] = graph[j];
                    id[j] = nextLeft++;
                }
                if ((right >> j & 1) == 1) {
                    id[j] = nextRight;
                    vertices[nextRight++] = j;
                }
            }
            for (int j = 0; j < nextRight; j++) {
                cGraph[j] = 0;
                for (int k = 0; k < n; k++) {
                    if ((graph[vertices[j]] >> k & 1) == 1) {
                        cGraph[j] += 1 << id[k];
                    }
                }
            }
            long component = 0;
            for (int j = 0; j < (1 << nextLeft); j++) {
                int rightDone = 0;
                for (int k = 0; k < nextLeft; k++) {
                    if ((j >> k & 1) == 1) {
                        rightDone |= cover[k];
                    }
                }
                int all = (1 << nextLeft) - 1 - j;
                int k = all;
                do {
                    current[k] = 0;
                    k = (k - 1) & all;
                } while (k != all);
                current[all] = 1;
                for (int l = 0; l < nextRight; l++) {
                    k = all;
                    do {
                        next[k] = 0;
                        k = (k - 1) & all;
                    } while (k != all);
                    k = all;
                    do {
                        if (current[k] != 0) {
                            int nk = k - (k & cGraph[l]);
                            next[nk] += current[k];
                            if ((rightDone >> vertices[l] & 1) == 1) {
                                next[k] += current[k];
                            }
                        }
                        k = (k - 1) & all;
                    } while (k != all);
                    long[] temp = current;
                    current = next;
                    next = temp;
                }
                component += current[0];
            }
            answer *= component;
            processed |= left | right;
        }
        out.printLine(answer);
    }
}
