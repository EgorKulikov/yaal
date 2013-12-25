package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	double[][] answer = new double[51][41];
	int length;
	int[] squares;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		length = in.readInt();
		int turns = in.readInt();
		squares = new int[length + 1];
		for (int i = 1; i <= length; i++) {
			String token = in.readString();
			if ("L".equals(token))
				squares[i] = Integer.MIN_VALUE;
			else
				squares[i] = Integer.parseInt(token);
		}
		ArrayUtils.fill(answer, -1);
		double result = calculate(0, turns);
		if (result > 0.5 + GeometryUtils.epsilon)
			out.printFormat("Bet for. %.4f\n", result);
		else if (result < 0.5 - GeometryUtils.epsilon)
			out.printFormat("Bet against. %.4f\n", result);
		else
			out.printFormat("Push. %.4f\n", result);
    }

	private double calculate(int position, int turns) {
		if (position > length)
			return 1;
		if (turns <= 0)
			return 0;
		if (answer[position][turns] != -1)
			return answer[position][turns];
		return answer[position][turns] = (move(position + 1, turns) + move(position + 2, turns)) / 2;
	}

	private double move(int position, int turns) {
		if (position > length)
			return 1;
		if (squares[position] == Integer.MIN_VALUE)
			return calculate(position, turns - 2);
		else
			return calculate(position + squares[position], turns - 1);
	}
}
