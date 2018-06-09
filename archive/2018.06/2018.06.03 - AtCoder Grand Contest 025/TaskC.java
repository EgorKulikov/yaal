package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Math.abs;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] l = new int[n];
        int[] r = new int[n];
        in.readIntArrays(l, r);
        long answer = 0;
        for (int i = 0; i < 2; i++) {
            long current = 0;
            int at = 0;
            NavigableSet<Integer> byLeft = new TreeSet<>((a, b) -> l[a] == l[b] ? a - b : l[b] - l[a]);
            NavigableSet<Integer> byRight = new TreeSet<>((a, b) -> r[b] == r[a] ? b - a : r[a] - r[b]);
            for (int j = 0; j < n; j++) {
                byLeft.add(j);
                byRight.add(j);
            }
            for (int j = 0; j < n; j++) {
                int next;
                if (j % 2 == 0) {
                    next = byLeft.first();
                } else {
                    next = byRight.first();
                }
                byLeft.remove(next);
                byRight.remove(next);
                if (at < l[next]) {
                    current += l[next] - at;
                    at = l[next];
                } else if (at > r[next]) {
                    current += at - r[next];
                    at = r[next];
                }
            }
            current += abs(at);
            answer = Math.max(answer, current);
            for (int j = 0; j < n; j++) {
                int temp = l[j];
                l[j] = -r[j];
                r[j] = -temp;
            }
        }
        out.printLine(answer);
    }
}
