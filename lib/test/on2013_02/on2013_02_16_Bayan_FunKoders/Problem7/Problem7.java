package on2013_02.on2013_02_16_Bayan_FunKoders.Problem7;



import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Problem7 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		out.printLine(MultiplicativeFunction.PHI.calculate(number));
    }
}
