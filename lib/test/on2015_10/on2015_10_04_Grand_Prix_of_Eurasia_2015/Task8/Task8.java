package on2015_10.on2015_10_04_Grand_Prix_of_Eurasia_2015.Task8;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task8 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int s = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        IOUtils.readIntArrays(in, a, b);
        ArrayUtils.orderBy(a, b);
        int[] answer = new int[s + 1];
        int[] last = new int[s + 1];
        Arrays.fill(answer, Integer.MIN_VALUE);
        answer[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = s; j >= b[i]; j--) {
                int candidate = answer[j - b[i]];
                if (last[j - b[i]] != a[i]) {
                    candidate++;
                }
                if (candidate > answer[j]) {
                    answer[j] = candidate;
                    last[j] = a[i];
                }
            }
        }
        if (answer[s] < 0) {
            out.printLine("Impossible");
        } else {
            out.printLine(answer[s]);
        }
    }
}
