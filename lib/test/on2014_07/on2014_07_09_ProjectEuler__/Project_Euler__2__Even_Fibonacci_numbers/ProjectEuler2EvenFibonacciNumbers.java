package on2014_07.on2014_07_09_ProjectEuler__.Project_Euler__2__Even_Fibonacci_numbers;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ProjectEuler2EvenFibonacciNumbers {
	long[] fibonacci = IntegerUtils.generateFibonacci(40000000000000001L);
	long[] sum = new long[fibonacci.length];

	{
		sum[0] = fibonacci[0] % 2 == 0 ? fibonacci[0] : 0;
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + (fibonacci[i] % 2 == 0 ? fibonacci[i] : 0);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long upTo = in.readLong();
		int position = Arrays.binarySearch(fibonacci, upTo);
		if (position < 0) {
			position = -position - 2;
		}
		out.printLine(sum[position]);
    }
}
