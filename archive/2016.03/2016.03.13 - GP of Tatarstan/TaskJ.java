package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] set = new long[10000000];
        NavigableSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            if (x < 0) {
                x = -x;
                if ((set[x >> 6] >> (x & 63) & 1) == 1) {
                    set[x >> 6] -= 1L << (x & 63);
                    int y = x - 1;
                    if ((set[y >> 6] >> (y & 63) & 1) == 1) {
                        free.add(x);
                    }
                    free.remove(x + 1);
                }
            } else {
                int p = in.readInt();
                if ((set[x >> 6] >> (x & 63) & 1) == 1) {
                    x = free.higher(x);
                }
                free.remove(x);
                boolean tillEnd = true;
                int end = x + p;
                for (int j = x; j < x + p; j++) {
                    if ((set[j >> 6] >> (j & 63) & 1) == 1) {
                        tillEnd = false;
                        end = j;
                        break;
                    }
                    set[j >> 6] += 1L << (j & 63);
                }
                out.printLine(x, end - 1);
                if (tillEnd) {
                    x += p;
                    if ((set[x >> 6] >> (x & 63) & 1) == 0) {
                        free.add(x);
                    }
                }
            }
        }
    }
}
