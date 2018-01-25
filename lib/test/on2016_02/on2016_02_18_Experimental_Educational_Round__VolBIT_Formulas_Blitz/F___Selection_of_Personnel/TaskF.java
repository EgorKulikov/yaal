package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.F___Selection_of_Personnel;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long answer = 0;
        long[][] c = IntegerUtils.generateBinomialCoefficients(n + 1);
        for (int i = 5; i <= 7; i++) {
            answer += c[n][i];
        }
        out.printLine(answer);
    }
}
