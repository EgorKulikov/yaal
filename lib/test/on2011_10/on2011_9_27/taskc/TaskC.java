package on2011_10.on2011_9_27.taskc;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
	private long[] fibonacci = IntegerUtils.generateFibonacci(46, -1);

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int stringIndex = in.readInt();
		long letterIndex = in.readLong();
		out.println(go(stringIndex, letterIndex));
	}

	private char go(int stringIndex, long letterIndex) {
		if (stringIndex < 2)
			return (char) ('a' + stringIndex);
		if (letterIndex <= fibonacci[stringIndex - 2])
			return go(stringIndex - 2, letterIndex);
		return go(stringIndex - 1, letterIndex - fibonacci[stringIndex - 2]);
	}
}
