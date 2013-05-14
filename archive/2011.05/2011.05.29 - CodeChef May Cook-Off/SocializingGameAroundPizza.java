import net.egork.utils.Solver;

import java.io.PrintWriter;

public class SocializingGameAroundPizza implements Solver {
	private int[] result;

	public SocializingGameAroundPizza() {
		result = new int[10001];
		int[] values = new int[20000];
		for (int i = 2; i <= 10000; i++) {
			for (int j = 0; j <= i - 2; j++)
				values[(result[j] ^ result[i - j - 2])] = i;
			for (int j = 0; ; j++) {
				if (values[j] != i) {
					result[i] = j;
					break;
				}
			}
		}
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		if (result[count] == 0)
			out.println("Bhima");
		else
			out.println("Arjuna");
	}
}

