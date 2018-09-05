package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class E {
    int[] first;
    int[] next;
    int[] last;
    int[] to;
    int[] degree;
    int enabled;
    boolean[] good;
    boolean[] inQueue;
    int[] queue;
    int size;
    int k;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        k = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        in.readIntArrays(x, y);
        decreaseByOne(x, y);
        first = createArray(n, -1);
        next = new int[2 * m];
        last = createArray(2 * m, -1);
        to = new int[2 * m];
        for (int i = 0; i < m; i++) {
            to[2 * i] = y[i];
            next[2 * i] = first[x[i]];
            first[x[i]] = 2 * i;
            if (next[2 * i] != -1) {
                last[next[2 * i]] = 2 * i;
            }
            to[2 * i + 1] = x[i];
            next[2 * i + 1] = first[y[i]];
            first[y[i]] = 2 * i + 1;
            if (next[2 * i + 1] != -1) {
                last[next[2 * i + 1]] = 2 * i + 1;
            }
        }
        degree = new int[n];
        for (int i = 0; i < m; i++) {
            degree[x[i]]++;
            degree[y[i]]++;
        }
        enabled = n;
        good = createArray(n, true);
        queue = new int[n];
        inQueue = new boolean[n];
        size = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] < k) {
                queue[size++] = i;
                inQueue[i] = true;
            }
        }
        processQueue();
        int[] answer = new int[m];
        answer[m - 1] = enabled;
        for (int i = m - 1; i > 0; i--) {
            if (good[x[i]] && good[y[i]]) {
                removeEdge(2 * i);
                removeEdge(2 * i + 1);
                size = 0;
                if (degree[x[i]] < k) {
                    queue[size++] = x[i];
                }
                if (degree[y[i]] < k) {
                    queue[size++] = y[i];
                }
                processQueue();
            }
            answer[i - 1] = enabled;
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }

    private void removeEdge(int edge) {
        degree[to[edge ^ 1]]--;
        int n = next[edge];
        int l = last[edge];
        if (n != -1) {
            last[n] = l;
        }
        if (l != -1) {
            next[l] = n;
        } else {
            first[to[edge ^ 1]] = n;
        }
    }

    private void processQueue() {
        for (int i = 0; i < size; i++) {
            int vertex = queue[i];
            good[vertex] = false;
            enabled--;
            for (int j = first[vertex]; j != -1; j = next[j]) {
                removeEdge(j ^ 1);
                if (degree[to[j]] < k && !inQueue[to[j]]) {
                    queue[size++] = to[j];
                    inQueue[to[j]] = true;
                }
            }
        }
    }
}
