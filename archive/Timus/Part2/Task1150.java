package Timus.Part2;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1150 implements Solver {
	private int[] result;
	private int[] tens;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String n = in.readString();
		result = new int[10];
		tens = new int[10];
		tens[0] = 1;
		for (int i = 1; i < 10; i++)
			tens[i] = 10 * tens[i - 1];
		for (int i = 1; i < n.length(); i++)
			add(i, false);
		for (int i = 0; i < n.length(); i++) {
			for (int j = i == 0 ? 1 : 0; j < n.charAt(i) - '0'; j++) {
				add(n.length() - i - 1, true);
				for (int k = 0; k < i; k++)
					result[n.charAt(k) - '0'] += tens[n.length() - i - 1];
				result[j] += tens[n.length() - i - 1];
			}
		}
		for (int i = 0; i < n.length(); i++)
			result[n.charAt(i) - '0']++;
		for (int value : result)
			out.println(value);
	}

	private void add(int digits, boolean fullZero) {
		if (digits == 0)
			return;
		if (fullZero) {
			for (int i = 0; i < 10; i++)
				result[i] += digits * tens[digits - 1];
			return;
		}
		if (digits == 1) {
			for (int i = 1; i < 10; i++)
				result[i]++;
			return;
		}
		result[0] += (digits - 1) * tens[digits - 2] * 9;
		for (int i = 1; i < 10; i++)
			result[i] += (digits - 1) * tens[digits - 2] * 9 + tens[digits - 1];
	}
}

