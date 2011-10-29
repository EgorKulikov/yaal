import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int number = in.readInt();
		char[] literals = in.readString().toCharArray();
		char[] order = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		int[] count = new int[7];
		for (char literal : literals) {
			for (int i = 0; i < 7; i++) {
				if (literal == order[i])
					count[i]++;
			}
		}
		int[] value = {1, 5, 10, 50, 100, 500, 1000};
		for (int i = 6; i >= 0; i--) {
			if (count[i] != 0) {
				if (!go(number - value[i] * count[i], i - 1, count, "", generate(order[i], count[i]), value, order, out))
					out.println("NO");
				return;
			}
		}
	}

	private boolean go(int number, int step, int[] count, String answerLeft, String answerRight, int[] value, char[] order, PrintWriter out) {
		if (step < 0) {
			if (number == 0) {
				out.println(answerLeft + answerRight);
				return true;
			}
			return false;
		}
		for (int i = 0; i <= count[step]; i++) {
			if (go(number - value[step] * (count[step] - 2 * i), step - 1, count, answerLeft + generate(order[step], i), answerRight + generate(order[step], count[step] - i), value, order, out))
				return true;
		}
		return false;
	}

	private String generate(char c, int repetitions) {
		StringBuilder builder = new StringBuilder(repetitions);
		for (int i = 0; i < repetitions; i++)
			builder.append(c);
		return builder.toString();
	}
}

