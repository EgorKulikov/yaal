package on2015_10.on2015_10_05_October_Challenge_2015.Spheres;



import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Spheres {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int c = in.readInt();
        int[] u = IOUtils.readIntArray(in, n);
        int[] l = IOUtils.readIntArray(in, m);
        MiscUtils.decreaseByOne(u, l);
        int[] ways = new IntArray(u).qty(c).join(new IntArray(l).qty(c), (a, b) -> (int)((long)a * b % MOD)).toArray();
        long[] answer = new long[c + 1];
        answer[0] = 1;
        for (int i = 0; i < c; i++) {
            for (int j = c; j > 0; j--) {
                answer[j] += answer[j - 1] * ways[i];
                answer[j] %= MOD;
            }
        }
        ArrayUtils.reverse(answer);
        answer = Arrays.copyOf(answer, c - 1);
        ArrayUtils.reverse(answer);
        answer = Arrays.copyOf(answer, c);
        out.printLine(answer);
    }
}
