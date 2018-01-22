package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readIntArray;

public class Greedy {
    int[] min;
    int[] delta;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] c = in.readIntArray(n);
        min = new int[4 * n];
        delta = new int[4 * n];
        init(0, 0, n - 1, n);
        for (int i = 0; i < n; i++) {
            update(0, 0, n - 1, c[i]);
            if (min[0] <= 0) {
                out.printLine(n - i - 1);
                return;
            }
        }
        out.printLine(0);
    }

    private void update(int root, int left, int right, int to) {
        if (left > to) {
            return;
        }
        if (right <= to) {
            change(root, 1);
            return;
        }
        pushDown(root);
        int middle = (left + right) >> 1;
        update(2 * root + 1, left, middle, to);
        update(2 * root + 2, middle + 1, right, to);
        update(root);
    }

    private void pushDown(int root) {
        change(2 * root + 1, delta[root]);
        change(2 * root + 2, delta[root]);
        delta[root] = 0;
    }

    private void change(int root, int by) {
        min[root] -= by;
        delta[root] += by;
    }

    private void init(int root, int left, int right, int n) {
        if (left == right) {
            min[root] = n - left;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, n);
            init(2 * root + 2, middle + 1, right, n);
            update(root);
        }
    }

    private void update(int root) {
        min[root] = Math.min(min[2 * root + 1], min[2 * root + 2]);
    }
}
