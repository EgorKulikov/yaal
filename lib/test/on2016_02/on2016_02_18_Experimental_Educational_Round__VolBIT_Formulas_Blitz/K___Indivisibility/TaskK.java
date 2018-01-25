package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.K___Indivisibility;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.MultiplicativeFunction;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long x = 1;
        for (int i = 2; i <= 10; i++) {
            x = IntegerUtils.lcm(i, x);
        }
        long phi = MultiplicativeFunction.PHI.calculate(x);
        long answer = (n / x) * phi;
        for (long i = (n / x) * x; i <= n; i++) {
            if (IntegerUtils.gcd(i, x) == 1) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
