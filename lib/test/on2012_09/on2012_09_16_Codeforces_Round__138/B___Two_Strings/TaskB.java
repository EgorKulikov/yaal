package on2012_09.on2012_09_16_Codeforces_Round__138.B___Two_Strings;



import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		char[] t = in.readString().toCharArray();
		int[] maxPrefix = prefix(s, t);
		Collections.reverse(Array.wrap(s));
		Collections.reverse(Array.wrap(t));
		int[] maxSuffix = prefix(s, t);
		for (int i = 0; i < s.length; i++) {
			if (maxPrefix[i] + maxSuffix[s.length - i - 1] <= t.length) {
				out.printLine("No");
				return;
			}
		}
		out.printLine("Yes");
	}

	private int[] prefix(char[] s, char[] t) {
		int[][] last = new int[26][t.length + 1];
		for (int i = 0; i < 26; i++) {
			char current = (char) ('a' + i);
			int position = -1;
			for (int j = 0; j < t.length; j++) {
				if (t[j] == current)
					position = j + 1;
				last[i][j + 1] = position;
			}
		}
		int index = 0;
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			if (index != t.length && s[i] == t[index])
				index++;
			result[i] = last[s[i] - 'a'][index];
		}
		return result;
	}
}
