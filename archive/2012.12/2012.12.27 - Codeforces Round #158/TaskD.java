package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] color = new int[count];
        final int[] sum = new int[count];
        IOUtils.readIntArrays(in, color, sum);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (sum[o1] != sum[o2])
                    return sum[o1] - sum[o2];
                return o1 - o2;
            }
        };
        NavigableSet<Integer> white = new TreeSet<Integer>(comparator);
        NavigableSet<Integer> black = new TreeSet<Integer>(comparator);
        for (int i = 0; i < count; i++) {
            if (color[i] == 0)
                white.add(i);
            else
                black.add(i);
        }
        for (int i = 0; i < count - 1; i++) {
            if (white.size() < black.size()) {
                NavigableSet<Integer> temp = white;
                white = black;
                black = temp;
            }
            if (sum[white.first()] <= sum[black.first()]) {
                int j = white.pollFirst();
                int k = black.pollLast();
                sum[k] -= sum[j];
                out.printLine(j + 1, k + 1, sum[j]);
                black.add(k);
            } else {
                int j = black.pollFirst();
                int k = white.pollLast();
                sum[k] -= sum[j];
                out.printLine(j + 1, k + 1, sum[j]);
                white.add(k);
            }
        }
    }
}
