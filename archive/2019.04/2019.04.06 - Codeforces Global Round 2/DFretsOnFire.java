package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.generated.collections.list.LongArray;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

import static net.egork.misc.ArrayUtils.unique;

public class DFretsOnFire {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] s = in.readLongArray(n);
        new LongArray(s).sort();
        s = unique(s);
        long[] diff = new long[s.length - 1];
        for (int i = 0; i < diff.length; i++) {
            diff[i] = s[i + 1] - s[i];
        }
        new LongArray(diff).sort();
        LongIntervalTree tree = new SumIntervalTree(diff);
        int q = in.readInt();
        long[] answer = new long[q];
        for (int i = 0; i < q; i++) {
            long l = in.readLong();
            long r = in.readLong();
            long len = r - l + 1;
            int at = Arrays.binarySearch(diff, len);
            if (at < 0) {
                at = -at - 1;
            }
            answer[i] = tree.query(0, at - 1) + (diff.length - at + 1) * len;
        }
        out.printLine(answer);
    }
}
