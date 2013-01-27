package on2011_10.on2011_9_25.taskb;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int stringIndex = in.readInt() - 1;
		long letterIndex = in.readLong() - 1;
		int length = in.readInt();
		long[] fibonacci = IntegerUtils.generateFibonacci(stringIndex + 1, -1);
		StringBuilder result = new StringBuilder();
		for (long i = letterIndex; i < letterIndex + length && i < fibonacci[stringIndex]; i++)
			result.append(getLetter(fibonacci, stringIndex, i));
		out.println(result);
	}

	private char getLetter(long[] fibonacci, int stringIndex, long letterIndex) {
		if (stringIndex < 2)
			return (char) ('b' - stringIndex);
		if (letterIndex >= fibonacci[stringIndex - 1])
			return getLetter(fibonacci, stringIndex - 2, letterIndex - fibonacci[stringIndex - 1]);
		return getLetter(fibonacci, stringIndex - 1, letterIndex);
	}
}
