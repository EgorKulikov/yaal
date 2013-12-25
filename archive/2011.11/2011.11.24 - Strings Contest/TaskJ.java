package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int symbolHeight = in.readInt();
		int dashHeight = in.readInt();
		String expression = in.readLine();
		out.printLine(go(expression, symbolHeight, dashHeight));
	}

	private int go(String expression, int symbolHeight, int dashHeight) {
		boolean containsFraction = false;
		int up = 0;
		int down = 0;
		int answer = symbolHeight;
		for (int i = 0; i < expression.length(); i++) {
			if (expression.substring(i).startsWith("\\frac")) {
				containsFraction = true;
				for (int j = i + 5; j < expression.length(); j++) {
					if (expression.charAt(j) == ' ')
						continue;
					if (expression.charAt(j) != '{') {
						i = j;
						up = Math.max(up, symbolHeight);
						break;
					}
					int balance = 0;
					for (int k = j; k < expression.length(); k++) {
						if (expression.charAt(k) == '{')
							balance++;
						else if (expression.charAt(k) == '}')
							balance--;
						if (balance == 0) {
							up = Math.max(up, go(expression.substring(j + 1, k), symbolHeight, dashHeight));
							i = k;
							break;
						}
					}
					break;
				}
				for (int j = i + 1; j < expression.length(); j++) {
					if (expression.charAt(j) == ' ')
						continue;
					if (expression.charAt(j) != '{') {
						i = j;
						down = Math.max(down, symbolHeight);
						break;
					}
					int balance = 0;
					for (int k = j; k < expression.length(); k++) {
						if (expression.charAt(k) == '{')
							balance++;
						else if (expression.charAt(k) == '}')
							balance--;
						if (balance == 0) {
							down = Math.max(down, go(expression.substring(j + 1, k), symbolHeight, dashHeight));
							i = k;
							break;
						}
					}
					break;
				}
			} else if (expression.charAt(i) == '{') {
				int balance = 0;
				for (int j = i; j < expression.length(); j++) {
					if (expression.charAt(j) == '{')
						balance++;
					else if (expression.charAt(j) == '}')
						balance--;
					if (balance == 0) {
						answer = Math.max(answer, go(expression.substring(i + 1, j), symbolHeight, dashHeight));
						i = j;
						break;
					}
				}
			}
		}
		if (containsFraction)
			answer = Math.max(answer, up + dashHeight + down);
		return answer;
	}
}
