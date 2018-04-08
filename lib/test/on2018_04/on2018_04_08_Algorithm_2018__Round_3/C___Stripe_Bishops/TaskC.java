package on2018_04.on2018_04_08_Algorithm_2018__Round_3.C___Stripe_Bishops;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.binarySearch;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int h = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        in.readIntArrays(x, y);
        long answer = (long)n * (2 * h - 2);
        IntList evenList = new IntArrayList();
        IntList oddList = new IntArrayList();
        for (int i = 0; i < n; i++) {
            if (((x[i] + y[i]) & 1) == 0) {
                evenList.add(x[i] - y[i]);
            } else {
                oddList.add(x[i] - y[i]);
            }
        }
        evenList.sort();
        oddList.sort();
        int[] even = evenList.toArray();
        int[] odd = oddList.toArray();
        for (int i = 0; i < n; i++) {
            int from = (int) max(MIN_VALUE + 1, x[i] + y[i] - 2L * h + 2);
            int to = x[i] + y[i];
            if (((x[i] + y[i]) & 1) == 0) {
                answer -= find(even, from, to);
            } else {
                answer -= find(odd, from, to);
            }
        }
        out.printLine(answer + n);
    }

    private long find(int[] even, int from, int to) {
        return upTo(even, to) - upTo(even, from - 1);
    }

    private long upTo(int[] even, int to) {
        int result = binarySearch(even, to);
        if (result >= 0) {
            return result + 1;
        } else {
            return -result - 1;
        }
    }
}
