package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Signal {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int[] curMin0 = new int[4];
		int[] curMin1 = new int[4];
		int[] nextMin0 = new int[4];
		int[] nextMin1 = new int[4];
		for (int i = 0; i < 4; i++) {
			if ((s.charAt(0) == '.' || s.charAt(0) - '0' == (i & 1)) && (s.charAt(1) == '.' || s.charAt(1) - '0' == (i >> 1 & 1))) {
				curMin0[i] = 2 - Integer.bitCount(i);
				curMin1[i] = Integer.bitCount(i);
			} else {
				curMin0[i] = s.length();
				curMin1[i] = s.length();
			}
		}
		for (int i = 2; i < s.length(); i++) {
			Arrays.fill(nextMin0, s.length());
			Arrays.fill(nextMin1, s.length());
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					if (s.charAt(i) != '.' && s.charAt(i) != '0' + k)
						continue;
					if (k == 0 && j == 0 || k == 1 && j == 3)
						continue;
					int nxt = (j >> 1) + (k << 1);
					nextMin0[nxt] = Math.min(nextMin0[nxt], curMin0[j] + 1 - k);
					nextMin1[nxt] = Math.min(nextMin1[nxt], curMin1[j] + k);
				}
			}
			int[] temp = curMin0;
			curMin0 = nextMin0;
			nextMin0 = temp;
			temp = curMin1;
			curMin1 = nextMin1;
			nextMin1 = temp;
		}
		int min0 = s.length();
		for (int i : curMin0)
			min0 = Math.min(min0, i);
		int min1 = s.length();
		for (int i : curMin1)
			min1 = Math.min(min1, i);
		if (min0 <= s.length() / 2 && min1 <= s.length() / 2)
			out.printLine("Yes");
		else
			out.printLine("No");
	}
}
