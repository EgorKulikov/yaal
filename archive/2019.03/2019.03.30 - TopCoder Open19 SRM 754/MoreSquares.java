package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.generated.collections.pair.IntIntPair;

import java.util.Set;

import static java.util.Arrays.copyOf;

public class MoreSquares {
    public int countLocations(int n, int SX, int SY, int[] Xprefix, int[] Yprefix) {
        int[] x = copyOf(Xprefix, n);
        int[] y = copyOf(Yprefix, n);
        for (int i = Xprefix.length; i < n; i++) {
            x[i] = (x[i - 1] * 47 + 42) % SX;
            y[i] = (y[i - 1] * 47 + 42) % SY;
        }
        Set<IntIntPair> has = new EHashSet<>();
        for (int i = 0; i < n; i++) {
            has.add(new IntIntPair(x[i], y[i]));
        }
        System.err.println(has.size());
        Set<IntIntPair> answer = new EHashSet<>();
        for (IntIntPair a : has) {
            for (IntIntPair b : has) {
                int dx = a.first - b.first;
                int dy = a.second - b.second;
                int nx = b.first + dy;
                int ny = b.second - dx;
                IntIntPair key = new IntIntPair(nx, ny);
                if (has.contains(key)) {
                    nx += dx;
                    ny += dy;
                    key = new IntIntPair(nx, ny);
                    if (!has.contains(key)) {
                        answer.add(key);
                    }
                }
            }
        }
        return answer.size();
    }
}
