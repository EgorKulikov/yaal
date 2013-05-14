package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] a = readArray(in);
		int n = in.readInt();
		for (int tn = 0; tn < n; tn++) {
			int[] b = readArray(in);
			int max = 0;
			for (int d = -(a.length - 1); d <= b.length - 1; d++) {
				int s = 0;
				for (int i = 0; i < a.length; i++) {
					int j = (i + d);
					if (j >= 0 && j < b.length) {
						if (a[i] == b[j]) s++;
					}
				}
				max = Math.max(max, s);
			}
			out.printLine(max * 1.0 / b.length);
		}
	}

	private int[] readArray(InputReader in) {
		int n = in.readInt();
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			String s = in.readString();
			int d = 0;
			if (s.charAt(s.length() - 1) == '-') {
				d = -1;
				s = s.substring(0, s.length() - 1);
			} else if (s.charAt(s.length() - 1) == '+') {
				d = 1;
				s = s.substring(0, s.length() - 1);
			}
			int nt = "C D EF G A B".indexOf(s.charAt(s.length() - 1));
			int oc = s.charAt(0) - '0';
			res[i] = oc * 12 + nt + d;
		}
		return res;

	}
}
