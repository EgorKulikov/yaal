package net.egork.timus;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1280 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int subjectCount = in.readInt();
		int limitationCount = in.readInt();
		int[] before = new int[limitationCount];
		int[] after = new int[limitationCount];
		in.readIntArrays(before, after);
		int[] order = in.readIntArray(subjectCount);
		int[] index = new int[subjectCount];
		for (int i = 0; i < subjectCount; i++)
			index[order[i] - 1] = i;
		for (int i = 0; i < limitationCount; i++) {
			if (index[before[i] - 1] >= index[after[i] - 1]) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
	}
}

