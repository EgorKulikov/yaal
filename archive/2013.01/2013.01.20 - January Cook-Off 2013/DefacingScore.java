package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DefacingScore {
	int[] type = {
		(1 << 0) + (1 << 1) + (1 << 2) + (0 << 3) + (1 << 4) + (1 << 5) + (1 << 6),
		(0 << 0) + (0 << 1) + (1 << 2) + (0 << 3) + (0 << 4) + (1 << 5) + (0 << 6),
		(1 << 0) + (0 << 1) + (1 << 2) + (1 << 3) + (1 << 4) + (0 << 5) + (1 << 6),
		(1 << 0) + (0 << 1) + (1 << 2) + (1 << 3) + (0 << 4) + (1 << 5) + (1 << 6),
		(0 << 0) + (1 << 1) + (1 << 2) + (1 << 3) + (0 << 4) + (1 << 5) + (0 << 6),
		(1 << 0) + (1 << 1) + (0 << 2) + (1 << 3) + (0 << 4) + (1 << 5) + (1 << 6),
		(1 << 0) + (1 << 1) + (0 << 2) + (1 << 3) + (1 << 4) + (1 << 5) + (1 << 6),
		(1 << 0) + (0 << 1) + (1 << 2) + (0 << 3) + (0 << 4) + (1 << 5) + (0 << 6),
		(1 << 0) + (1 << 1) + (1 << 2) + (1 << 3) + (1 << 4) + (1 << 5) + (1 << 6),
		(1 << 0) + (1 << 1) + (1 << 2) + (1 << 3) + (0 << 4) + (1 << 5) + (1 << 6)
	};

	boolean[][] can = new boolean[10][10];

	{
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				can[i][j] = (type[i] & type[j]) == type[i];
		}
	}

	int answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		final int from = in.readInt();
		int to = in.readInt();
		if (to == 0) {
			out.printLine(0);
			return;
		}
		answer = from;
		String ss;
		String sf = StringUtils.reverse(Integer.toString(to));
		check(ss = StringUtils.reverse(Integer.toString(from)), sf);
		int cc = 1;
		for (int j = 0; j < ss.length(); j++)
			cc *= 10;
		while (cc <= to) {
			cc *= 10;
			ss = '?' + ss;
			check(ss, sf);
		}
//		new NumberIterator() {
//			@Override
//			protected void process(long prefix, int remainingDigits) {
//				long prefixCopy = prefix;
//				for (int i1 = 0; i1 < samples.size(); i1++) {
//					String s = samples.get(i1);
//					prefix = prefixCopy;
//					int result = 0;
//					int ten = 1;
//					for (int i = 0; i < remainingDigits; i++) {
//						if (i >= s.length() || s.charAt(i) == '?') {
//							result += ten * 9;
//							ten *= 10;
//							continue;
//						}
//						int last = s.charAt(i) - '0';
//						if (can[last][9])
//							result += ten * 9;
//						else
//							result += ten * 8;
//						ten *= 10;
//					}
//					result += prefix * ten;
//					int i;
//					boolean good = true;
//					for (i = remainingDigits; prefix != 0 && i < s.length(); i++) {
//						if (s.charAt(i) == '?') {
//							prefix /= 10;
//							continue;
//						}
//						int fromDigit = s.charAt(i) - '0';
//						int toDigit = (int) (prefix % 10);
//						if (!can[fromDigit][toDigit]) {
//							good = false;
//							break;
//						}
//						prefix /= 10;
//					}
//					if (i < s.length() || !good)
//						continue;
//					answer = Math.max(answer, result);
//				}
//			}
//		}.run(1, to);
		out.printLine(answer);
	}

	int[] current = new int[2];

	private void check(String ss, String sf) {
		current[0] = 0;
		current[1] = -1;
		for (int i = sf.length() - 1; i >= 0; i--) {
			if (current[0] == -1 && current[1] == -1)
				return;
			int toDigit = sf.charAt(i) - '0';
			if (i >= ss.length() || ss.charAt(i) == '?') {
				current[1] *= 10;
				current[1] += 9;
				if (toDigit != 0 && current[0] != -1)
					current[1] = current[0] * 10 + toDigit - 1;
				if (current[0] != -1)
					current[0] = current[0] * 10 + toDigit;
				continue;
			}
			int fromDigit = ss.charAt(i) - '0';
			if (current[1] != -1) {
				current[1] *= 10;
				if (can[fromDigit][9])
					current[1] += 9;
				else
					current[1] += 8;
			}
			if (current[0] != -1) {
				for (int j = toDigit - 1; j >= 0; j--) {
					if (can[fromDigit][j]) {
						current[1] = current[0] * 10 + j;
						break;
					}
				}
				if (can[fromDigit][toDigit])
					current[0] = current[0] * 10 + toDigit;
				else
					current[0] = -1;
			}
		}
		if (current[0] != -1)
			answer = Math.max(answer, current[0]);
		else if (current[1] != -1)
			answer = Math.max(answer, current[1]);
	}
}
