package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        decreaseByOne(a);
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] == i) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            boolean[] was = new boolean[n];
            int current = 0;
            while (!was[current]) {
                was[current] = true;
                current = a[current];
            }
            root = current;
        }
        int answer = 0;
        if (a[root] != root) {
            a[root] = root;
            answer++;
        }
        int[] was = new int[n];
        was[root] = 2;
        for (int i = 0; i < n; i++) {
            if (was[i] != 0) {
                continue;
            }
            int current = i;
            while (was[current] == 0) {
                was[current] = 1;
                current = a[current];
            }
            boolean change = false;
            int at = -1;
            if (was[current] == 1) {
                change = true;
                at = current;
            }
            current = i;
            while (was[current] == 1) {
                was[current] = 2;
                current = a[current];
            }
            if (change) {
                a[at] = root;
                answer++;
            }
        }
        out.printLine(answer);
        for (int i = 0; i < n; i++) {
            a[i]++;
        }
        out.printLine(a);
    }
}
