package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JamBoard {
	private int columnCount;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int queryCount = in.readInt();
		int rowCount = in.readInt();
		columnCount = in.readInt();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(rowCount * columnCount);
		int[] connections = new int[rowCount * columnCount];
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'W') {
				int first = getCell(in);
				int second = getCell(in);
				first = setSystem.get(first);
				second = setSystem.get(second);
				if (first != second) {
					connections[first] += connections[second];
					setSystem.join(first, second);
				}
			} else if (type == 'V')
				connections[setSystem.get(getCell(in))]++;
			else if (type == 'R')
				connections[setSystem.get(getCell(in))]--;
			else if (type == 'L') {
				int first = getCell(in);
				int second = getCell(in);
				first = setSystem.get(first);
				second = setSystem.get(second);
				if (connections[first] == 0 ^ connections[second] == 0)
					out.printLine("ON");
				else
					out.printLine("OFF");
			}
		}
	}

	private int getCell(InputReader in) {
		return decode(in.readCharacter(), in.readCharacter(), in.readCharacter(), in.readCharacter());
	}

	private int decode(char c1, char c2, char r1, char r2) {
		int column = decode(c1) * 52 + decode(c2) - 1;
		int row = decode(r1) * 52 + decode(r2) - 1;
		return row / 5 * columnCount + column;
	}

	private int decode(char c) {
		if (c <= 'Z')
			return c - 'A';
		return 26 + c - 'a';
	}
}
