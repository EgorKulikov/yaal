package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	static final int INF = (int) 1e9;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.next();
		int n = in.readInt();
		String[] words = new String[n];
		for (int i = 0; i < n; ++i) {
			words[i] = in.next();
		}
		int[][] minKill = new int[s.length() + 1][];
		for (int i = s.length(); i >= 0; --i) {
			String suffix = s.substring(i);
			int[][] otherAns = new int[suffix.length() + 1][];
			System.arraycopy(minKill, i, otherAns, 0, otherAns.length);
			minKill[i] = doit(suffix, otherAns, words);
		}
		out.printLine(minKill[0][s.length()]);
    }

	private int[] doit(String s, int[][] suffixAns, String[] words) {
		int[][][] could = new int[s.length() + 1][words.length][];
		for (int pos = 0; pos <= s.length(); ++pos)
			for (int wi = 0; wi < words.length; ++wi) {
				int minPos = Math.max(0, words[wi].length() - (s.length() - pos));
				int maxPos = Math.min(pos, words[wi].length());
				if (minPos <= maxPos) {
					could[pos][wi] = new int[maxPos - minPos + 1];
					Arrays.fill(could[pos][wi], INF);
					if (pos == 0 && minPos == 0)
						could[pos][wi][0] = 0;
				}
			}
		for (int pos = 0; pos <= s.length(); ++pos)
			for (int wi = 0; wi < words.length; ++wi) {
				if (could[pos][wi] == null) continue;
				int[] ccould = could[pos][wi];
				String cword = words[wi];
				int minPos = Math.max(0, cword.length() - (s.length() - pos));
				for (int wpos = minPos; wpos < minPos + ccould.length; ++wpos) {
					int oldBest = ccould[wpos - minPos];
					if (oldBest >= INF) continue;
					if (pos < s.length() && wpos < cword.length() && s.charAt(pos) == cword.charAt(wpos)) {
						int newMinPos = Math.max(0, cword.length() - (s.length() - (pos + 1)));
						could[pos + 1][wi][wpos + 1 - newMinPos] = Math.min(could[pos + 1][wi][wpos + 1 - newMinPos], oldBest);
					}
					if (pos > 0) {
						int[] killCost = suffixAns[pos];
						for (int willKill = 1; willKill < killCost.length; ++willKill) {
							int newMinPos = Math.max(0, cword.length() - (s.length() - (pos + willKill)));
							if (wpos < newMinPos) break;
							int curCost = killCost[willKill];
							if (curCost >= INF) continue;
							could[pos + willKill][wi][wpos - newMinPos] = Math.min(could[pos + willKill][wi][wpos - newMinPos], oldBest + curCost);
						}
					}
				}
			}
		int[] ret = new int[s.length() + 1];
		Arrays.fill(ret, INF);
		for (int pos = 1; pos <= s.length(); ++pos) {
			for (int wi = 0; wi < words.length; ++wi) {
				int minPos = Math.max(0, words[wi].length() - (s.length() - pos));
				int maxPos = Math.min(pos, words[wi].length());
				if (minPos <= maxPos && maxPos == words[wi].length()) {
					ret[pos] = Math.min(ret[pos], could[pos][wi][maxPos - minPos] + 1);
				}
			}
		}
		return ret;
	}
}
