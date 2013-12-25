package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class FindTheGoalkeeper {
	static int[] answer = new int[10000000];

	static {
		Arrays.fill(answer, -1);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(josephProblem(count, 2) + 1);
	}

	public static int josephProblem(int n, int k) {
		if (answer[n] != -1)
			return answer[n];
		if (n == 1)
			return answer[n] = 0;
		if (k == 1)
			return answer[n] = n - 1;
		if (k > n)
			return answer[n] = ((josephProblem(n - 1, k) + k) % n);
		int count = n / k;
		int result = answer[n] = josephProblem(n - count, k);
		result -= n % k;
		if (result < 0)
			result += n;
		else
			result += result / (k - 1);
		return answer[n] = result;
	}

}
