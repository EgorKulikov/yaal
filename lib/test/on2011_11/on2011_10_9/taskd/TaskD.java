package on2011_11.on2011_10_9.taskd;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private long[] fibonacci = IntegerUtils.generateFibonacci(1000000000000000000L);
	private boolean[] digits = new boolean[fibonacci.length];
	private long[] taken = new long[fibonacci.length];
	private long[] notTaken = new long[fibonacci.length];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long number = in.readLong();
		for (int i = fibonacci.length - 1; i > 0; i--) {
			digits[i] = number >= fibonacci[i];
			if (number >= fibonacci[i])
				number -= fibonacci[i];
		}
		int lastIndex = 0;
		int length = 0;
		if (digits[1])
			lastIndex = 1;
		if (digits[2])
			lastIndex = 2;
		taken[lastIndex] = 1;
		notTaken[lastIndex] = 0;
		for (int i = lastIndex + 1; i < fibonacci.length; i++) {
			length++;
			if (digits[i]) {
				taken[i] = taken[lastIndex] + notTaken[lastIndex];
				notTaken[i] = taken[lastIndex] * ((length - 1) / 2) + notTaken[lastIndex] * (length / 2);
				length = 0;
				lastIndex = i;
			}
		}
		out.printLine(taken[lastIndex] + notTaken[lastIndex]);
	}
}
