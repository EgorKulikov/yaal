package Timus.Part5;

import net.egork.arrays.ArrayUtils;
import net.egork.arrays.ArrayWrapper;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1433 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		long firstOddity = ArrayUtils.countUnorderedPairs(ArrayWrapper.wrap(first)) % 2;
		long secondOddity = ArrayUtils.countUnorderedPairs(ArrayWrapper.wrap(second)) % 2;
		Arrays.sort(first);
		Arrays.sort(second);
		if (!Arrays.equals(first, second)) {
			out.println("different");
			return;
		}
		for (int i = 1; i < 4; i++) {
			if (first[i - 1] == first[i]) {
				out.println("equal");
				return;
			}
		}
		if (firstOddity == secondOddity)
			out.println("equal");
		else
			out.println("different");
	}
}

