package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] count = new int[10];
		int c;
		while (Character.isDigit(c = in.read()))
			count[c - '0']++;
		int answer = 0;
		int at = -1;
		for (int i = 1; i <= 5; i++) {
			if (count[i] != 0 && count[10 - i] != 0) {
				int current = go(count, i);
				if (current > answer) {
					answer = current;
					at = i;
				}
			}
		}
		if (at == -1) {
			printZeroes(count, out);
			printZeroes(count, out);
		} else {
			printAnswer(count, at, out);
		}
	}

	private void printAnswer(int[] count, int at, OutputWriter out) {
		int[] firstCount = count.clone();
		int[] secondCount = count.clone();
		firstCount[at]--;
		secondCount[10 - at]--;
		StringBuilder first = new StringBuilder();
		StringBuilder second = new StringBuilder();
		first.append(at);
		second.append(10 - at);
		for (int i = 0; i < 10; i++) {
			int current = Math.min(firstCount[i], secondCount[9 - i]);
			firstCount[i] -= current;
			secondCount[9 - i] -= current;
			for (int j = 0; j < current; j++) {
				first.append(i);
				second.append(9 - i);
			}
		}
		first.reverse();
		second.reverse();
		for (int i = Math.min(firstCount[0], secondCount[0]); i > 0; i--) {
			first.append(0);
			second.append(0);
			firstCount[0]--;
			secondCount[0]--;
		}
		first.reverse();
		second.reverse();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < firstCount[i]; j++)
				first.append(i);
			for (int j = 0; j < secondCount[i]; j++)
				second.append(i);
		}
		first.reverse();
		second.reverse();
		out.printLine(first);
		out.printLine(second);
	}

	private void printZeroes(int[] count, OutputWriter out) {
		for (int i = 9; i >= 0; i--) {
			for (int j = 0; j < count[i]; j++)
				out.print(i);
		}
		out.printLine();
	}

	private int go(int[] count, int at) {
		int[] firstCount = count.clone();
		int[] secondCount = count.clone();
		int answer = 1;
		firstCount[at]--;
		secondCount[10 - at]--;
		for (int i = 0; i < 10; i++) {
			int current = Math.min(firstCount[i], secondCount[9 - i]);
			firstCount[i] -= current;
			secondCount[9 - i] -= current;
			answer += current;
		}
		answer += Math.min(firstCount[0], secondCount[0]);
		return answer;
	}
}
