package on2013_01.on2013_01_23_SnarkNews_Summer_Series_Round__4.Cars;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Cars {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long count = in.readLong();
        long answer = 0;
        long[] fibonacci = IntegerUtils.generateFibonacci(count);
        for (int i = fibonacci.length - 1; i > 0; i--) {
            if (count < fibonacci[i])
                continue;
            long qty = (count - fibonacci[i]) / i + 1;
            answer += qty;
            count -= qty * i;
        }
        out.printLine(answer);
    }
}
