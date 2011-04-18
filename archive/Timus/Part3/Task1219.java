package Timus.Part3;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1219 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] oneCount = new int[26];
		int[] twoCount = new int[26 * 26];
		int[] threeCount = new int[26 * 26 * 26];
		out.print("ab");
		oneCount[0]++;
		oneCount[1]++;
		threeCount[1] += 20;
		int preLast = 0;
		int last = 1;
		for (int i = 2; i < 1000000; i++) {
			int sumTwoLast = 26 * 26 * preLast + 26 * last;
			int sumOneLast = 26 * last;
			int minSum = Integer.MAX_VALUE;
			int letter = -1;
			for (int j = 0; j < 26; j++) {
				int current = oneCount[j] + twoCount[sumOneLast + j] + threeCount[sumTwoLast + j];
				if (current < minSum) {
					minSum = current;
					letter = j;
				}
			}
			out.print((char)('a' + letter));
			oneCount[letter]++;
			twoCount[sumOneLast + letter] += 20;
			threeCount[sumTwoLast + letter] += 400;
			preLast = last;
			last = letter;
		}
		for (int count : oneCount) {
			if (count > 40000)
				throw new RuntimeException();
		}
		for (int count : twoCount) {
			if (count > 40000)
				throw new RuntimeException();
		}
		for (int count : threeCount) {
			if (count > 40000)
				throw new RuntimeException();
		}
		out.println();
	}
}

