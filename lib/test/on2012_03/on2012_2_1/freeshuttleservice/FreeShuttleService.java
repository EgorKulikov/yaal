package on2012_03.on2012_2_1.freeshuttleservice;



import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FreeShuttleService {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(MultiplicativeFunction.PHI.calculate(count));
	}
}
