package Timus.Part1;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1061 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (n < k) {
			out.println(0);
			return;
		}
		char[] memory = in.readTable(1, n)[0];
		long currentResult = 0;
		long bestResult = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < k; i++) {
			if (memory[i] == '*')
				currentResult += Integer.MAX_VALUE;
			else
				currentResult += memory[i] - '0';
		}
		if (currentResult < bestResult) {
			bestResult = currentResult;
			index = 0;
		}
		for (int i = k; i < n; i++) {
			if (memory[i] == '*')
				currentResult += Integer.MAX_VALUE;
			else
				currentResult += memory[i] - '0';
			if (memory[i - k] == '*')
				currentResult -= Integer.MAX_VALUE;
			else
				currentResult -= memory[i - k] - '0';
			if (currentResult < bestResult) {
				bestResult = currentResult;
				index = i - k + 1;
			}
		}
		out.println(index + 1);
	}
}

