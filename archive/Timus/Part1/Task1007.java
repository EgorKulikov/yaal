package Timus.Part1;

import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Task1007 implements Solver {
	private int n;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		if (testNumber == 1)
			n = in.readInt();
		char[] code;
		try {
			code = in.readString().toCharArray();
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		int remainder = 0;
		for (int i = 0; i < code.length; i++) {
			if (code[i] == '1')
				remainder += i + 1;
		}
		remainder %= n + 1;
		if (code.length == n - 1) {
			int countOnes = 0;
			for (int i = n - 1; i >= 0; i--) {
				if (i < n - 1 && code[i] == '1')
					countOnes++;
				if ((remainder + i + 1 + countOnes) % (n + 1) == 0) {
					for (int j = 0; j < i; j++)
						out.print(code[j]);
					out.print('1');
					for (int j = i; j < n - 1; j++)
						out.print(code[j]);
					out.println();
					return;
				} else if ((remainder + countOnes) % (n + 1) == 0) {
					for (int j = 0; j < i; j++)
						out.print(code[j]);
					out.print('0');
					for (int j = i; j < n - 1; j++)
						out.print(code[j]);
					out.println();
					return;
				}
			}
			throw new RuntimeException();
		}
		if (code.length == n + 1) {
			int countOnes = 0;
			for (int i = n; i >= 0; i--) {
				if (code[i] == '1') {
					if ((remainder - i - 1 - countOnes) % (n + 1) == 0) {
						for (int j = 0; j < i; j++)
							out.print(code[j]);
						for (int j = i + 1; j <= n; j++)
							out.print(code[j]);
						out.println();
						return;
					}
					countOnes++;
				} else {
					if ((remainder - countOnes) % (n + 1) == 0) {
						for (int j = 0; j < i; j++)
							out.print(code[j]);
						for (int j = i + 1; j <= n; j++)
							out.print(code[j]);
						out.println();
						return;
					}
				}
			}
			throw new RuntimeException();
		}
		if (remainder == 0) {
			out.println(new String(code));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (code[i] == '1' && (remainder - i - 1) % (n + 1) == 0) {
				for (int j = 0; j < i; j++)
					out.print(code[j]);
				out.print('0');
				for (int j = i + 1; j < n; j++)
					out.print(code[j]);
				out.println();
				return;
			}
		}
		throw new RuntimeException();
	}
}

