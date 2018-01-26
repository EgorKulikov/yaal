package net.egork;

import net.egork.collections.intcollection.Heap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] c = readIntArray(in, n);
        Heap heap = new Heap((a, b) -> c[b] - c[a], n);
        long total = 0;
        int[] answer = new int[n];
        for (int i = 0; i < k; i++) {
            heap.add(i);
        }
        for (int i = 0; i < n; i++) {
            if (i + k < n) {
                heap.add(i + k);
            }
            int current = heap.poll();
            answer[current] = i + k + 1;
            total += (long)(i + k - current) * c[current];
        }
        out.printLine(total);
        out.printLine(answer);
    }
}
