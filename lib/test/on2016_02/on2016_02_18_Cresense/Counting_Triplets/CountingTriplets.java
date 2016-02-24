package on2016_02.on2016_02_18_Cresense.Counting_Triplets;



import net.egork.collections.set.TreapSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class CountingTriplets {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int s = in.readInt();
        int answer = 0;
        NavigableSet<Number> set = new TreapSet<>();
        for (int i = 0; i < n; i++) {
            set.add(new Number(a[i], i));
        }
        for (int i = 0; i < n; i++) {
            set.remove(new Number(a[i], i));
            for (int j = 0; j < i; j++) {
                answer += set.headSet(new Number(s - a[i] - a[j], -1), false).size();
            }
        }
        out.printLine(answer);
    }

    static class Number implements Comparable<Number> {
        int n;
        int id;

        public Number(int n, int id) {
            this.n = n;
            this.id = id;
        }

        @Override
        public int compareTo(Number o) {
            if (n != o.n) {
                return n - o.n;
            }
            return id - o.id;
        }
    }
}
