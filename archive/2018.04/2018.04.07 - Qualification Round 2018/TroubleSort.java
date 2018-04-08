package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TroubleSort {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] v = in.readIntArray(n);
        IntList first = new IntArrayList();
        IntList second = new IntArrayList();
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                first.add(v[i]);
            } else {
                second.add(v[i]);
            }
        }
        first.sort();
        second.sort();
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                v[i] = first.get(i >> 1);
            } else {
                v[i] = second.get(i >> 1);
            }
        }
        int answer = -1;
        for (int i = 0; i < n - 1; i++) {
            if (v[i] > v[i + 1]) {
                answer = i;
                break;
            }
        }
        out.printLine("Case #" + testNumber + ":", answer == -1 ? "OK" : answer);
    }
}
