package on2012_12.on2012_12_17_USACO_December_Contest_Gold.First;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class First {
	int[] letterOrder = new int[26];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final String[] words = IOUtils.readStringArray(in, count);
		int total = 1;
		for (int i = 0; i < count; i++)
			total += words[i].length();
		boolean[] leaf = new boolean[total];
		int[][] children = new int[total][26];
		ArrayUtils.fill(children, -1);
		int size = 1;
		for (int i = 0; i < count; i++) {
			int root = 0;
			for (int j = 0; j < words[i].length(); j++) {
				int ch = words[i].charAt(j) - 'a';
				if (children[root][ch] == -1)
					children[root][ch] = size++;
				root = children[root][ch];
			}
			leaf[root] = true;
		}
		List<String> answer = new ArrayList<String>();
		int[] relation = new int[26];
		for (int i = 0; i < count; i++) {
			int root = 0;
			Arrays.fill(relation, 0);
			boolean good = true;
			for (int j = 0; j < words[i].length(); j++) {
				if (leaf[root]) {
					good = false;
					break;
				}
				int ch = words[i].charAt(j) - 'a';
				for (int k = 0; k < 26; k++) {
					if (k != ch && children[root][k] != -1) {
						if ((relation[k] >> ch & 1) != 0) {
							good = false;
							break;
						}
						relation[ch] |= 1 << k;
					}
				}
				if (!good)
					break;
				root = children[root][ch];
			}
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 26; k++) {
					if ((relation[k] >> j & 1) != 0)
						relation[k] |= relation[j];
				}
			}
			for (int j = 0; j < 26; j++) {
				if ((relation[j] >> j & 1) != 0)
					good = false;
			}
			if (good)
				answer.add(words[i]);
		}
		out.printLine(answer.size());
		for (String s : answer)
			out.printLine(s);
	}
}
