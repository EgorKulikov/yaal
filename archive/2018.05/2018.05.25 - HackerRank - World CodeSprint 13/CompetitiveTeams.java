package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.misc.ArrayUtils.createArray;

public class CompetitiveTeams {
    boolean change;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        int[] size = createArray(n, 1);
        int[] sizes = new int[n + 1];
        NavigableSet<Integer> order = new TreeSet<>();
        order.add(1);
        sizes[1] = n;
        setSystem.setListener(((joinedRoot, root) -> {
            if (--sizes[size[root]] == 0) {
                change = true;
                order.remove(size[root]);
            }
            if (--sizes[size[joinedRoot]] == 0) {
                change = true;
                order.remove(size[joinedRoot]);
            }
            size[root] += size[joinedRoot];
            if (sizes[size[root]]++ == 0) {
                change = true;
                order.add(size[root]);
            }
        }));
        int[] sequence = new int[n];
        sequence[0] = 1;
        int length = 1;
        int q = in.readInt();
        int teamCount = n;
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            if (type == 1) {
                int x = in.readInt() - 1;
                int y = in.readInt() - 1;
                change = false;
                if (setSystem.join(x, y)) {
                    teamCount--;
                }
                if (change) {
                    length = 0;
                    for (int j : order) {
                        sequence[length++] = j;
                    }
                }
            } else {
                int c = in.readInt();
                int k = 0;
                long answer = 0;
                if (c == 0) {
                    answer = (long)teamCount * (teamCount - 1) / 2;
                } else {
                    int remaining = teamCount;
                    for (int j = 0; j < length; j++) {
                        while (k < length && sequence[k] - sequence[j] < c) {
                            remaining -= sizes[sequence[k++]];
                        }
                        answer += (long)remaining * sizes[sequence[j]];
                    }
                }
                out.printLine(answer);
            }
        }
    }
}
