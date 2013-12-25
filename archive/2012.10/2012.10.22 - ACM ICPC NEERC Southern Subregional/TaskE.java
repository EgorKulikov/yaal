package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int zeroCount = in.readInt();
		int oneCount = in.readInt();
		char[] password = in.readString().toCharArray();
		if (zeroCount + oneCount > count) {
			out.printLine(-1);
			return;
		}
		int[] quantity = new int[3];
		for (char c : password)
			quantity[c - '0']++;
		int[][] replace = new int[3][3];
		if (quantity[0] > zeroCount) {
			if (quantity[1] > oneCount) {
				replace[0][2] = quantity[0] - zeroCount;
				replace[1][2] = quantity[1] - oneCount;
			} else {
				replace[0][1] = Math.min(quantity[0] - zeroCount, oneCount - quantity[1]);
				replace[0][2] = quantity[0] - zeroCount - replace[0][1];
				replace[2][1] = oneCount - quantity[1] - replace[0][1];
			}
		} else {
			if (quantity[1] < oneCount) {
				replace[2][0] = zeroCount - quantity[0];
				replace[2][1] = oneCount - quantity[1];
			} else {
				replace[1][0] = Math.min(quantity[1] - oneCount, zeroCount - quantity[0]);
				replace[1][2] = quantity[1] - oneCount - replace[1][0];
				replace[2][0] = zeroCount - quantity[0] - replace[1][0];
			}
		}
		int answer = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				answer += replace[i][j];
		}
		out.printLine(answer);
		for (int i = 0; i < count; i++) {
			int current = password[i] - '0';
			for (int j = 0; j < 3; j++) {
				if (replace[current][j] != 0) {
					password[i] = (char) ('0' + j);
					replace[current][j]--;
					break;
				}
			}
		}
		out.printLine(password);
	}
}
