package Timus.Part8;

import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1731 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] first = new int[n];
		for (int i = 0; i < n; i++)
			first[i] = i + 1;
		int[] second = new int[m];
		for (int i = 0; i < m; i++)
			second[i] = (i + 1) * (n + 1);
		IOUtils.printArray(first, out);
		IOUtils.printArray(second, out);
	}
}

