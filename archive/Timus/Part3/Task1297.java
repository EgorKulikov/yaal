package Timus.Part3;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1297 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String message = in.readString();
		String result = "";
		for (int i = 0; i < message.length(); i++) {
			for (int j = 0; j <= i && j + i < message.length(); j++) {
				if (message.charAt(i + j) != message.charAt(i - j))
					break;
				if (2 * j + 1 > result.length())
					result = message.substring(i - j, i + j + 1);
			}
			for (int j = 0; j < i && j + i < message.length(); j++) {
				if (message.charAt(i + j) != message.charAt(i - j - 1))
					break;
				if (2 * j + 2 > result.length())
					result = message.substring(i - j - 1, i + j + 1);
			}
		}
		out.println(result);
	}
}

