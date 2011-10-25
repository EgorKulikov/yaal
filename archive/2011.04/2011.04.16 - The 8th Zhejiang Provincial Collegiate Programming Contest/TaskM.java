package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskM implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		double[] numbers = in.readDoubleArray(size);
		Arrays.sort(numbers);
		double result;
		if (size % 2 == 1)
			result = numbers[size / 2];
		else
			result = (numbers[size / 2] + numbers[size / 2 - 1]) / 2;
		out.printf("%.3f\n", result);
	}
}

