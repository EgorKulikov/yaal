package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] a = new int[4 * count];
        int[] b = new int[4 * count];
        int[] c = new int[4 * count];
        IOUtils.readIntArrays(in, a, b, c);
        MiscUtils.decreaseByOne(a, b, c);
        NavigableSet<Integer> present = new TreeSet<>();
        for (int i = 0; i < 4 * count; i++) {
            present.add(i);
        }
        int[][] queries = new int[count][8];
        int[] size = new int[count];
        for (int i = 0; i < 4 * count; i++) {
            queries[b[i]][size[b[i]]++] = i;
            queries[c[i]][size[c[i]]++] = i;
        }
        int[] order = new int[4 * count];
        int at = 4 * count - 1;
        while (!present.isEmpty()) {
            int current = present.pollFirst();
            order[at--] = current + 1;
            int id = a[current];
            while (true) {
                boolean found = false;
                for (int j : queries[id]) {
                    if (present.contains(j)) {
                        present.remove(j);
                        order[at--] = j + 1;
                        id = a[j];
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    break;
                }
            }
        }
        out.printLine("YES");
        out.printLine(order);
    }
}
