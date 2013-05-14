package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int count = in.readInt();
		int linesPerPage = in.readInt();
		int charactersPerLine = in.readInt();
		int lineCount = 1;
		int remainingCharacters = charactersPerLine + 1;
		for (int i = 0; i < count; i++) {
			String word = in.readString();
			int length = word.length();
			if (remainingCharacters > length)
				remainingCharacters -= length + 1;
			else {
				lineCount++;
				remainingCharacters = charactersPerLine - length;
			}
		}
		out.printLine((lineCount + linesPerPage - 1) / linesPerPage);
	}
}
