package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndCake {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int minLength = in.readInt();
        int queryCount = in.readInt();
        long[] array = new long[count];
        long a = in.readLong();
        long b = in.readLong();
        long c = in.readLong();
        long d = in.readLong();
        long e = in.readLong();
        long f = in.readLong();
        long r = in.readLong();
        long s = in.readLong();
        long t = in.readLong();
        long m = in.readLong();
        array[0] = in.readLong();
        long power = t;
        for (int i = 1; i < count; i++) {
            power *= t;
            power %= s;
            if (power <= r) {
                array[i] = (a * array[i - 1] % m * array[i - 1] + b * array[i - 1] + c) % m;
            } else {
                array[i] = (d * array[i - 1] % m * array[i - 1] + e * array[i - 1] + f) % m;
            }
        }
        int l1 = in.readInt();
        int la = in.readInt();
        int lc = in.readInt();
        int lm = in.readInt();
        int d1 = in.readInt();
        int da = in.readInt();
        int dc = in.readInt();
        int dm = in.readInt();
        int[] from = new int[queryCount];
        int[] to = new int[queryCount];
        for (int i = 0; i < queryCount; i++) {
            l1 = (int) (((long)la * l1 + lc) % lm);
            d1 = (int) (((long)da * d1 + dc) % dm);
            from[i] = l1;
            to[i] = Math.min(from[i] + minLength - 1 + d1, count - 1);
        }
        long[] min = new long[(count + minLength - 1) / minLength];
        Arrays.fill(min, Long.MAX_VALUE);
        for (int i = 0; i < count; i++) {
            min[i / minLength] = Math.min(min[i / minLength], array[i]);
        }
        long[] up = new long[count];
        long current = Long.MAX_VALUE;
        for (int i = count - 1; i >= 0; i--) {
            current = Math.min(current, array[i]);
            up[i] = current;
            if (i % minLength == 0) {
                current = Long.MAX_VALUE;
            }
        }
        long[] down = new long[count];
        current = Long.MAX_VALUE;
        for (int i = 0; i < count; i++) {
            if (i % minLength == 0) {
                current = Long.MAX_VALUE;
            }
            current = Math.min(current, array[i]);
            down[i] = current;
        }
        long sum = 0;
        long product = 1;
        for (int i = 0; i < queryCount; i++) {
            current = Math.min(up[from[i]], down[to[i]]);
            int id = from[i] / minLength + 1;
            if (id < to[i] / minLength) {
                current = Math.min(current, min[id]);
            }
            sum += current;
            product *= current;
            product %= MOD;
        }
        out.printLine(sum, product);
    }
}
