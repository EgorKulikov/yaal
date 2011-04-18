package Timus.Part7;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1654 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] cipher = in.readString().toCharArray();
		char[] result = new char[cipher.length];
		int size = 0;
		for (char letter : cipher) {
			if (size != 0 && result[size - 1] == letter) {
				size--;
			} else {
				result[size++] = letter;
			}
		}
		out.println(new String(Arrays.copyOf(result, size)));
	}
}

