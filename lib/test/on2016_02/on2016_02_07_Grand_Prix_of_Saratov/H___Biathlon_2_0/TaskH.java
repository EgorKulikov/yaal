package on2016_02.on2016_02_07_Grand_Prix_of_Saratov.H___Biathlon_2_0;



import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        readIntArrays(in, a, b);
        int m = in.readInt();
        int[] c = new int[m];
        int[] d = new int[m];
        readIntArrays(in, c, d);
        int[] order = createOrder(m);
        sort(order, (x, y) -> {
            long left = (long) c[x] * d[y];
            long right = (long) c[y] * d[x];
            return Long.compare(left, right);
        });
        IntList hull = new IntArrayList();
        for (int i : order) {
            if (hull.isEmpty()) {
                hull.add(i);
            } else {
                while (hull.size() >= 2) {
                    if (over(c, d, hull.get(hull.size() - 2), hull.last(), i)) {
                        hull.popLast();
                    } else {
                        break;
                    }
                }
                hull.add(i);
            }
        }
        order = createOrder(n);
        sort(order, (x, y) -> {
            long left = (long) a[x] * b[y];
            long right = (long) a[y] * b[x];
            return Long.compare(left, right);
        });
        long[] answer = new long[n];
        int best = 0;
        long value = Long.MAX_VALUE;
        for (int i = 0; i < hull.size(); i++) {
            int id = hull.get(i);
            long cc = c[id];
            long dd = d[id];
            long cur = a[order[0]] * cc + b[order[0]] * dd;
            if (cur < value) {
                best = i;
                value = cur;
            }
        }
        for (int i : order) {
            int id = hull.get(best);
            long cc = c[id];
            long dd = d[id];
            value = a[i] * cc + b[i] * dd;
            while (true) {
                int last = best - 1;
                if (last < 0) {
                    last += hull.size();
                }
                int cId = hull.get(last);
                long c0 = c[cId];
                long d0 = d[cId];
                long cur = a[i] * c0 + b[i] * d0;
                if (cur >= value) {
                    break;
                }
                value = cur;
                cc = c0;
                dd = d0;
                best = last;
            }
            while (true) {
                int last = best + 1;
                if (last >= hull.size()) {
                    last -= hull.size();
                }
                int cId = hull.get(last);
                long c0 = c[cId];
                long d0 = d[cId];
                long cur = a[i] * c0 + b[i] * d0;
                if (cur >= value) {
                    break;
                }
                value = cur;
                cc = c0;
                dd = d0;
                best = last;
            }
            answer[i] = value;
        }
        out.printLine(answer);
    }

    private boolean over(int[] c, int[] d, int left, int middle, int right) {
        long ax = c[left];
        long ay = d[left];
        long bx = c[middle];
        long by = d[middle];
        long cx = c[right];
        long cy = d[right];
        return ax * (by - cy) + bx * (cy - ay) + cx * (ay - by) <= 0;
    }
}
