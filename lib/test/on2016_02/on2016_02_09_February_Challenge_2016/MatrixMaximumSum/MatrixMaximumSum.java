package on2016_02.on2016_02_09_February_Challenge_2016.MatrixMaximumSum;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class MatrixMaximumSum {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int[] b = readIntArray(in, n);
        for (int i = 0; i < n; i++) {
            a[i] += i + 1;
            b[i] += i + 1;
        }
        long[] sa = solve(n, a);
        long[] sb = solve(n, b);
        long[] answer = new long[n];
        for (int i = 0; i < n; i++) {
            answer[i] = sa[i] * sb[i] % MOD;
        }
        out.printLine(answer);
    }

    private long[] solve(int n, int[] a) {
        long[] answer = new long[n];
        long sum = sumArray(a);
        answer[0] = sum % MOD;
        int[] grow = new int[n];
        int gSize = 0;
        int[] decline = new int[n];
        int dSize = 0;
        int[] qty = createArray(n, 1);
        int[] delta = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] > a[i - 1]) {
                delta[i]++;
            }
            if (i < n - 1 && a[i] >= a[i + 1]) {
                delta[i]++;
            }
            if (delta[i] == 0) {
                decline[dSize++] = i;
            } else if (delta[i] == 2) {
                grow[gSize++] = i;
            }
        }
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
        for (int i = 1; i < n; i++) {
            int gShift = 0;
            for (int j = 0; j < gSize; j++) {
                if (delta[grow[j]] != 2) {
                    gShift++;
                    continue;
                }
                qty[grow[j]]++;
                sum += a[grow[j]];
                grow[j - gShift] = grow[j];
            }
            gSize -= gShift;
            IntSet toRemove = new IntHashSet();
            int dShift = 0;
            for (int j = 0; j < dSize; j++) {
                if (delta[decline[j]] != 0) {
                    dShift++;
                    continue;
                }
                qty[decline[j]]--;
                sum -= a[decline[j]];
                if (qty[decline[j]] == 0) {
                    toRemove.add(decline[j]);
                    dShift++;
                    qty[decline[j]] = 1;
                } else {
                    decline[j - dShift] = decline[j];
                }
            }
            dSize -= dShift;
            IntSet toUpdate = new IntHashSet();
            for (int j : toRemove) {
                Integer lower = set.lower(j);
                if (lower != null && !toRemove.contains(lower)) {
                    toUpdate.add(lower);
                }
                Integer higher = set.higher(j);
                if (higher != null && !toRemove.contains(higher)) {
                    toUpdate.add(higher);
                }
                set.remove(j);
            }
            for (int j : toUpdate) {
                int wasDelta = delta[j];
                delta[j] = 0;
                Integer lower = set.lower(j);
                if (lower != null && a[j] > a[lower]) {
                    delta[j]++;
                }
                Integer higher = set.higher(j);
                if (higher != null && a[j] >= a[higher]) {
                    delta[j]++;
                }
                if (delta[j] == 0 && wasDelta != 0) {
                    decline[dSize++] = j;
                } else if (delta[j] == 2 && wasDelta != 2) {
                    grow[gSize++] = j;
                }
            }
            sum %= MOD;
            if (sum < 0) {
                sum += MOD;
            }
            answer[i] = sum;
        }
        return answer;
    }
}
