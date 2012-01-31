package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		StringBuilder string = new StringBuilder(in.readString());
		int adds = in.readInt();
		boolean containsAlphaNumeric = false;
		for (int i = 0; i < string.length(); i++) {
			if (Character.isLetterOrDigit(string.charAt(i))) {
				containsAlphaNumeric = true;
				break;
			}
		}
		for (int it = 0; it < adds; it++) {
			if (!containsAlphaNumeric) {
				string.setCharAt(string.length() - 1, (char) (string.charAt(string.length() - 1) + 1));
				if (Character.isLetterOrDigit(string.charAt(string.length() - 1)))
					containsAlphaNumeric = true;
			} else {
				boolean carry = true;
				int leftMostIndex = -1;
				for (int i = string.length() - 1; i >= 0; i--) {
					char ch = string.charAt(i);
					if (Character.isLetterOrDigit(ch)) {
						if (ch == '9')
							ch = '0';
						else if (ch == 'Z')
							ch = 'A';
						else if (ch == 'z')
							ch = 'a';
						else {
							ch++;
							carry = false;
						}
						string.setCharAt(i, ch);
						leftMostIndex = i;
						if (!carry)
							break;
					}
				}
				if (carry) {
					char ch = string.charAt(leftMostIndex);
					if (ch == '0')
						ch = '1';
					string.insert(leftMostIndex, ch);
				}
			}
			int length = string.length();
			for (int i = 0; i < length; i++)
				out.print(string.charAt(i));
			out.println();
		}
		out.println();
	}
}

