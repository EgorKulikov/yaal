package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.TreeSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        TreeSet<int[]>[] sets = new TreeSet[8];
        for (int i = 0; i < 8; i++) {
            final int m = i;
            sets[i] = new TreeSet<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    int d = 0;
                    for (int i = 0; i < 4; i++) {
                        if ((m & (1 << i)) == 0) {
                            d += o1[i] - o2[i];
                        } else {
                            d += o2[i] - o1[i];
                        }
                    }
                    if (d != 0) return d;
                    for (int i = 0; i < 4; i++) {
                        if (o1[i] != o2[i]) {
                            return o1[i] - o2[i];
                        }
                    }
                    return 0;
                }
            });
        }
        for (int i = 0; i < n; i++) {
            int t = in.readInt();
            int[] q = new int[4];
            for (int j = 0; j < 4; j++) {
                q[j] = in.readInt();
            }
            if (t == 1) {
                for (int j = 0; j < 8; j++) {
                    sets[j].add(q);
                }
            } else if (t == 2) {
                for (int j = 0; j < 8; j++) {
                    sets[j].remove(q);
                }
            } else {
                int res = 0;
                for (int j = 0; j < 8; j++) {
                    int[] w = sets[j].first();
                    int d = 0;
                    for (int k = 0; k < 4; k++) {
                        d += Math.abs(w[k] - q[k]);
                    }
                    res = Math.max(res, d);
                    w = sets[j].last();
                    d = 0;
                    for (int k = 0; k < 4; k++) {
                        d += Math.abs(w[k] - q[k]);
                    }
                    res = Math.max(res, d);
                }
                out.printLine(res);
            }
        }
    }
}
