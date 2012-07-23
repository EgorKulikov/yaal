package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AuthenticationFailed {
	static final int MOD = 1009419529;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int canSkip = in.readInt();
		char[] s = IOUtils.readCharArray(in, length + canSkip);
		int[][] count = new int[length + canSkip + 1][canSkip + 1];
		count[0][0] = 1;
		int[] last = new int[length + canSkip];
		int[] lastPosition = new int[26];
		Arrays.fill(lastPosition, Integer.MIN_VALUE / 2);
		for (int i = 0; i < length + canSkip; i++) {
			last[i] = lastPosition[s[i] - 'a'];
			lastPosition[s[i] - 'a'] = i;
		}
		for (int i = 0; i < length + canSkip; i++) {
			count[i + 1][0] = 1;
			for (int j = 1; j <= canSkip; j++) {
				count[i + 1][j] = count[i][j - 1] + count[i][j];
				if (count[i + 1][j] >= MOD)
					count[i + 1][j] -= MOD;
				int l = last[i];
				int delta = i - l;
				if (delta <= j && i != j - 1) {
					count[i + 1][j] -= count[l][j - delta];
					if (count[i + 1][j] < 0)
						count[i + 1][j] += MOD;
				}
			}
		}
		int answer = count[length + canSkip][canSkip];
		answer--;
		if (answer < 0)
			answer += MOD;
		out.printLine(answer);
	}
}
