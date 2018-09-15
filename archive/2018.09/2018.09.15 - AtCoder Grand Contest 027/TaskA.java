package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sort;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int x = in.readInt();
        int[] a = in.readIntArray(n);
        sort(a);
        int answer = 0;
        while (answer < n) {
            if (a[answer] > x) {
                break;
            }
            x -= a[answer++];
        }
        if (answer == n && x != 0) {
            answer--;
        }
        out.printLine(answer);
    }
}
