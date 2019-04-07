package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Math.min;

public class EPavelITreugolniki {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        NavigableSet<Integer> odd = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 1) {
                odd.add(i);
            }
        }
        long answer = 0;
        int at = 0;
        for (int i = n - 1; i >= at; i--) {
            if (a[i] == 1) {
                odd.remove(i);
            }
            if (a[i] <= 1) {
                continue;
            }
            while (a[i] >= 2 && !odd.isEmpty()) {
                int other = odd.pollLast();
                a[other]--;
                a[i] -= 2;
                answer++;
            }
            while (a[i] >= 2 && at < i) {
                int current = min(a[i] >> 1, a[at]);
                a[i] -= 2 * current;
                a[at] -= current;
                answer += current;
                if (a[at] > 0) {
                    break;
                }
                at++;
            }
            if (at == i) {
                answer += a[i] / 3;
                break;
            }
        }
        out.printLine(answer);
    }
}
