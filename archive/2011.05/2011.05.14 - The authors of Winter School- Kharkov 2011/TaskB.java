import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] a = in.readString().toCharArray();
		char[] b = in.readString().toCharArray();
		int index = in.readInt();
		int[] first = new int[10001];
		int[] second = new int[10001];
		for (int i = 0; i < a.length; i++)
			first[a.length - i - 1] = a[i] - '0';
		for (int i = 0; i < b.length; i++)
			second[b.length - i - 1] = b[i] - '0';
		int carry = 0;
		for (int i = 0; i < index; i++) {
			int end = Math.min(i, 10000);
			for (int j = Math.max(0, i - 10000); j <= end; j++)
				carry += first[j] * second[i - j];
			carry /= 10;
		}
		out.println(carry);
	}
}

