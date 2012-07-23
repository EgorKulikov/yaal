import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] string = in.readString().toCharArray();
		int badSubStringsCount = in.readInt();
		char[][] subStrings = new char[badSubStringsCount][];
		for (int i = 0; i < badSubStringsCount; i++)
			subStrings[i] = in.readString().toCharArray();
		int lastEnd = string.length;
		int maxLength = 0;
		int position = 0;
		for (int i = string.length - 1; i >= 0; i--) {
			for (char[] subString : subStrings) {
				if (i + subString.length <= lastEnd && starts(string, i, subString))
					lastEnd = i + subString.length - 1;
			}
			if (lastEnd - i > maxLength) {
				maxLength = lastEnd - i;
				position = i;
			}
		}
		out.println(maxLength + " " + position);
	}

	private boolean starts(char[] string, int index, char[] subString) {
		for (char letter : subString) {
			if (string[index++] != letter) {
				return false;
			}
		}
		return true;
	}
}

