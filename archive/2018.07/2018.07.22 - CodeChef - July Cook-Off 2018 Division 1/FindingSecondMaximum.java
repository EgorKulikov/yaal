package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.PriorityQueue;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class FindingSecondMaximum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        int[] leader = createArray(n, -1);
        for (int i = 0; i < m; i++) {
            if (leader[b[i]] == -1) {
                leader[b[i]] = a[i];
            } else {
                leader[b[i]] = -2;
            }
        }
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            if (leader[i] >= 0) {
                size[leader[i]]++;
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (leader[i] == -1) {
                queue.add(size[i]);
            }
        }
        int answer = 0;
        while (queue.size() > 1) {
            queue.poll();
            queue.add(queue.poll() + 1);
            answer++;
        }
        answer += queue.poll() - 1;
        out.printLine(answer);
    }
}
