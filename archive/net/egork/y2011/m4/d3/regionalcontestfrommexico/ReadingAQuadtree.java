package net.egork.y2011.m4.d3.regionalcontestfrommexico;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class ReadingAQuadtree implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int side = in.readInt();
		char[] description = in.readString().toCharArray();
		if ((side & (side - 1)) != 0) {
			out.println("Invalid length");
			return;
		}
		boolean[][] isWhite = new boolean[side][side];
		readTree(description, 0, isWhite, 0, side, 0, side);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < side; i++) {
			int start = -1;
			for (int j = 0; j < side; j++) {
				if (!isWhite[i][j]) {
					if (start != -1) {
						if (start == j - 1)
							result.append("(").append(j).append(",").append(i + 1).append("),");
						else
							result.append("(").append(start + 1).append("-").append(j).append(",").append(i + 1).append("),");
					}
					start = -1;
				} else {
					if (start == -1)
						start = j;
				}
			}
			if (start != -1) {
				if (start == side - 1)
					result.append("(").append(side).append(",").append(i + 1).append("),");
				else
					result.append("(").append(start + 1).append("-").append(side).append(",").append(i + 1).append("),");
			}
		}
		out.println(result.toString().substring(0, result.length() - 1));
	}

	private int readTree(char[] description, int position, boolean[][] white, int left, int right, int top, int bottom) {
		if (description[position] != '*') {
			if (description[position++] == '1') {
				for (int i = top; i < bottom; i++) {
					for (int j = left; j < right; j++)
						white[i][j] = true;
				}
			}
			return position;
		}
		position++;
		position = readTree(description, position, white, left, (left + right) / 2, top, (top + bottom) / 2);
		position = readTree(description, position, white, (left + right) / 2, right, top, (top + bottom) / 2);
		position = readTree(description, position, white, left, (left + right) / 2, (top + bottom) / 2, bottom);
		position = readTree(description, position, white, (left + right) / 2, right, (top + bottom) / 2, bottom);
		return position;
	}
}

