package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.DoubleUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		double[][] cost = new double[count][count];
		ArrayUtils.fill(cost, Double.NEGATIVE_INFINITY);
		for (int i = 0; i < count; i++) {
			cost[i][i] = 0;
		}
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			cost[from][to] = Math.max(cost[from][to], in.readInt());
		}
		double left = 0;
		double right = 1e6;
		double[][] current = new double[count][count];
		for (int i = 0; i < 40; i++) {
			double middle = (left + right) / 2;
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					current[j][k] = cost[j][k] - middle;
					if (j == k) {
						current[j][k] = 0;
					}
				}
			}
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					for (int l = 0; l < count; l++) {
						current[k][l] = Math.max(current[k][l], current[k][j] + current[j][l]);
					}
				}
			}
			boolean good = false;
			for (int j = 0; j < count; j++) {
				if (current[j][j] > 0) {
					good = true;
				}
			}
			if (good) {
				left = middle;
			} else {
				right = middle;
			}
		}
		out.printLine((left + right) / 2);
    }
}
