import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	private int result = Integer.MAX_VALUE;
	private int[] answerFirstRegister = new int[6];
	private int[] answerSecondRegister = new int[6];
	private int[] answerSecondCoefficient = new int[6];

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] achieved = new int[6];
		int[] firstRegister = new int[6];
		int[] secondRegister = new int[6];
		int[] secondCoefficient = new int[6];
		achieved[0] = 1;
		go(1, n, achieved, firstRegister, secondRegister, secondCoefficient);
		out.println(result);
		for (int i = 1; i <= result; i++) {
			out.print("lea e" + (char)('a' + i) + "x, [");
			if (answerSecondCoefficient[i] < 0)
				out.print(-answerSecondCoefficient[i] + "*");
			out.print("e" + (char)('a' + answerFirstRegister[i]) + "x");
			if (answerSecondCoefficient[i] > 0)
				out.print(" + " + answerSecondCoefficient[i] + "*e" + (char)('a' + answerSecondRegister[i]) + "x");
			out.println("]");
		}
	}

	private void go(int step, int target, int[] achieved, int[] firstRegister, int[] secondRegister, int[] secondCoefficient) {
		if (achieved[step - 1] == target) {
			result = step - 1;
			System.arraycopy(firstRegister, 0, answerFirstRegister, 0, step);
			System.arraycopy(secondRegister, 0, answerSecondRegister, 0, step);
			System.arraycopy(secondCoefficient, 0, answerSecondCoefficient, 0, step);
			return;
		}
		if (step == 6 || step >= result)
			return;
		for (int i = 0; i < step; i++) {
			firstRegister[step] = i;
			for (int k = 0; k <= 3; k++) {
				achieved[step] = achieved[i] << k;
				secondCoefficient[step] = -(1 << k);
				go(step + 1, target, achieved, firstRegister, secondRegister, secondCoefficient);
			}
			for (int j = 0; j < step; j++) {
				secondRegister[step] = j;
				for (int k = 0; k <= 3; k++) {
					achieved[step] = achieved[i] + (achieved[j] << k);
					secondCoefficient[step] = 1 << k;
					go(step + 1, target, achieved, firstRegister, secondRegister, secondCoefficient);
				}
			}
		}
	}
}

