import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		out.printLine("1/2");
	}
}
