package on2012_12.on2012_12_27_Codeforces_Round__158.E___Dividing_Kingdom;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.set.PersistentSet;
import net.egork.collections.set.PersistentTreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] x = new int[count];
        int[] y = new int[count];
        IOUtils.readIntArrays(in, x, y);
        int[] qty = IOUtils.readIntArray(in, 9);
        int[] order = ArrayUtils.order(x);
        PersistentSet<City> set = new PersistentTreapSet<City>();
        for (int i : order) {
            set.add(new City(x[i], y[i]));
            set.markState(x[i]);
        }
        ArrayUtils.sort(x, IntComparator.DEFAULT);
        ArrayUtils.sort(y, IntComparator.DEFAULT);
        x = ArrayUtils.unique(x);
        y = ArrayUtils.unique(y);
        City test = new City(1000000001, 0);
        int[] sortedQty = qty.clone();
        Arrays.sort(sortedQty);
        int[] currentQty = new int[9];
        int[] xx = new int[3];
        int[] yy = new int[3];
        xx[2] = x[x.length - 1];
        yy[2] = y[y.length - 1];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < 9; k++) {
                    if (i == k || j == k)
                        continue;
                    int firstRow = qty[i] + qty[j] + qty[k];
                    int left = 0;
                    int right = x.length - 1;
                    while (left <= right) {
                        int middle = (left + right) >> 1;
                        int curCount = set.getState(x[middle]).size();
                        if (curCount == firstRow) {
                            left = right = middle;
                            break;
                        } else if (firstRow > curCount)
                            left = middle + 1;
                        else
                            right = middle - 1;
                    }
                    if (left > right)
                        continue;
                    int x0 = x[left];

                    for (int l = 0; l < 9; l++) {
                        if (i == l || j == l || k == l)
                            continue;
                        for (int m = 0; m < 9; m++) {
                            if (i == m || j == m || k == m || l == m)
                                continue;
                            int firstColumn = qty[i] + qty[l] + qty[m];
                            left = 0;
                            right = y.length - 1;
                            while (left <= right) {
                                int middle = (left + right) >> 1;
                                test.y = y[middle];
                                int curCount = set.headSet(test, true).size();
                                if (curCount == firstColumn) {
                                    left = right = middle;
                                    break;
                                } else if (firstColumn > curCount)
                                    left = middle + 1;
                                else
                                    right = middle - 1;
                            }
                            if (left > right)
                                continue;
                            int y0 = y[left];
                            for (int r = 0; r < 9; r++) {
                                if (i == r || j == r || k == r || l == r || m == r)
                                    continue;
                                for (int s = 0; s < 9; s++) {
                                    if (i == s || j == s || k == s || l == s || m == s || r == s)
                                        continue;
                                    int nfr = firstRow + qty[l] + qty[r] + qty[s];
                                    left = 0;
                                    right = x.length - 1;
                                    while (left <= right) {
                                        int middle = (left + right) >> 1;
                                        int curCount = set.getState(x[middle]).size();
                                        if (curCount == nfr) {
                                            left = right = middle;
                                            break;
                                        } else if (nfr > curCount)
                                            left = middle + 1;
                                        else
                                            right = middle - 1;
                                    }
                                    if (left > right)
                                        continue;
                                    int x1 = x[left];
                                    for (int t = 0; t < 9; t++) {
                                        if (i == t || j == t || k == t || l == t || s == t || m == t || r == t || s == t)
                                            continue;
                            int nfc = firstColumn + qty[j] + qty[r] + qty[t];
                            left = 0;
                            right = y.length - 1;
                            while (left <= right) {
                                int middle = (left + right) >> 1;
                                test.y = y[middle];
                                int curCount = set.headSet(test, true).size();
                                if (curCount == nfc) {
                                    left = right = middle;
                                    break;
                                } else if (nfc > curCount)
                                    left = middle + 1;
                                else
                                    right = middle - 1;
                            }
                            if (left > right)
                                continue;
                            int y1 = y[left];
                            xx[0] = x0;
                            xx[1] = x1;
                            yy[0] = y0;
                            yy[1] = y1;
                                        boolean good = true;
                            for (int n = 0; n < 2 && good; n++) {
                                for (int o = 0; o < 3; o++) {
                                    int key = 3 * n + o;
                                    if (key == 2 || key == 5)
                                        continue;
                                    currentQty[key] = 0;
                                    for (int p = 0; p <= n; p++) {
                                        for (int q = 0; q <= o; q++) {
                                            if (p != n || q != o)
                                                currentQty[key] -= currentQty[p * 3 + q];
                                        }
                                    }
                                    test.y = yy[o];
                                    currentQty[key] += set.getState(xx[n]).headSet(test).size();
                                    if (key == 0 && currentQty[key] != qty[i] ||
                                            key == 1 && currentQty[key] != qty[j] ||
                                            key == 2 && currentQty[key] != qty[k] ||
                                            key == 3 && currentQty[key] != qty[l] ||
                                            key == 4 && currentQty[key] != qty[r] ||
                                            key == 5 && currentQty[key] != qty[s] ||
                                            key == 6 && currentQty[key] != qty[m] ||
                                            key == 7 && currentQty[key] != qty[t]) {
                                        good = false;
                                        break;
                                    }
                                }
                            }
                            if (good) {
                                out.printLine(x0 + .5, x1 + .5);
                                out.printLine(y0 + .5, y1 + .5);
                                return;
                            }
                            }
                            }
                            }
                        }
                    }
                }
            }
        }
        out.printLine(-1);
    }

    static class City implements Comparable<City> {
        int x, y;

        City(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(City o) {
            if (y != o.y)
                return y - o.y;
            return x - o.x;
        }
    }
}
