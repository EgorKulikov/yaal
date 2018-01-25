package on2016_08.on2016_08_22_August_Circuits.Dual_issues;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.isPrime;

public class DualIssues {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int answer = -1;
        for (int i : a) {
            if (isPrime(i)) {
                answer = Math.max(answer, i * i);
            }
        }
        out.printLine(answer);
    }
}
