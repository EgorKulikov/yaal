package on2016_03.on2016_03_13_GP_of_Tatarstan.M___The_Smallest_Fraction;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long x = 1;
        long y = 0;
        for (int i = 0; i < n; i++) {
            int a = in.readInt();
            int b = in.readInt();
            x = IntegerUtils.lcm(x, a);
            y = IntegerUtils.gcd(y, b);
        }
        out.printLine(x, y);
    }
}
