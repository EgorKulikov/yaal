package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String pattern = in.readString();
		String sample = in.readString();
		String big = sample + sample;
		String[] tokens = pattern.split("[*]+", -1);
		int[] answer = new int[big.length() + 1];
		for (int i = 0; i < answer.length; i++)
			answer[i] = i;
		int[] next = new int[answer.length];
		for (int i = tokens.length - 1; i >= 0; i--) {
			int last = Integer.MAX_VALUE;
			for (int k = answer.length - 1; k >= 0; k--) {
				if (startsWith(big, k, tokens[i]))
					last = answer[k + tokens[i].length()];
				next[k] = last;
			}
			int[] temp = next;
			next = answer;
			answer = temp;
		}
		int result = 0;
		for (int i = 0; i < sample.length(); i++) {
			if (answer[i] <= i + sample.length() && big.substring(i).startsWith(tokens[0]) && big.substring(0, i + sample.length()).endsWith(tokens[tokens.length - 1]) && (tokens.length != 1 || sample.length() == tokens[0].length()))
				result++;
		}
		out.printLine(result);
	}

	private boolean startsWith(String big, int k, String token) {
		if (k + token.length() > big.length())
			return false;
		for (int i = 0; i < token.length(); i++) {
			if (big.charAt(i + k) != token.charAt(i))
				return false;
		}
		return true;
	}
}
