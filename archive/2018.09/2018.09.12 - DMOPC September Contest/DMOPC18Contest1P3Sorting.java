package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class DMOPC18Contest1P3Sorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = in.readIntArray(n);
        decreaseByOne(p);
        List<IntList> answer = new ArrayList<>();
        while (true) {
            int[] q = p.clone();
            sort(q);
            if (Arrays.equals(p, q)) {
                break;
            }
            IntList current = new IntArrayList();
            boolean[] done = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!done[i] && p[i] != i) {
                    int j = i;
                    do {
                        current.add(j + 1);
                        j = p[j];
                        done[j] = true;
                    } while (j != i);
                }
            }
            int element = p[current.get(0) - 1];
            for (int i = 1; i < current.size(); i++) {
                int next = p[current.get(i) - 1];
                p[current.get(i) - 1] = element;
                element = next;
            }
            p[current.get(0) - 1] = element;
            answer.add(current);
        }
        out.printLine(answer.size());
        for (IntList list : answer) {
            out.print(list.size(), "");
            out.printLine(list);
        }
    }
}
