package on2016_07.on2016_07_19_Codeforces_Round__363__Div__1_.C___LRU;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static java.lang.Math.min;
import static net.egork.io.IOUtils.readDoubleArray;
import static net.egork.misc.ArrayUtils.count;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        double[] p = readDoubleArray(in, n);
        double[] answer = new double[1 << n];
        answer[0] = 1;
        for (int i = 1; i < (1 << n); i++) {
            double sum = 0;
            boolean bad = false;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0) {
                    sum += p[j];
                } else if (p[j] == 0) {
                    bad = true;
                    break;
                }
            }
            if (bad) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    answer[i] += answer[i - (1 << j)] * p[j] / (p[j] + sum);
                }
            }
        }
        double[] result = new double[n];
        int qty = min(k, n - count(p, 0));
        for (int i = 0; i < (1 << n); i++) {
            if (bitCount(i) == qty) {
                for (int j = 0; j < n; j++) {
                    if ((i >> j & 1) == 1) {
                        result[j] += answer[i];
                    }
                }
            }
        }
        out.printLine(result);
    }
}
