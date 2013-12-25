package on2011_10.on2011_9_27.taskd;



import net.egork.collections.sequence.StringWrapper;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskD {
	private long[] fibonacci = IntegerUtils.generateFibonacci(50, -1);

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		if (testNumber != 1)
			out.println();
		String first = in.readString();
		String second = in.readString();
		int index = in.readInt();
		long firstCoefficient = index > 1 ? fibonacci[index - 2] : 1 - index;
		long secondCoefficient = index != 0 ? fibonacci[index - 1] : 0;
		long[] result = new long[26];
		for (char c : StringWrapper.wrap(first))
			result[c - 'a'] += firstCoefficient;
		for (char c : StringWrapper.wrap(second))
			result[c - 'a'] += secondCoefficient;
		for (int i = 0; i < 26; i++)
			out.println(((char)('a' + i)) + ":" + result[i]);
	}
}
