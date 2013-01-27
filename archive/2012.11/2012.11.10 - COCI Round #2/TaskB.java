package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String[] words = IOUtils.readStringArray(in, 6);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < 6; k++) {
					if (i == k || j == k)
						continue;
					int mask = (1 << i) + (1 << j) + (1 << k);
					boolean good = true;
					for (int l = 0; l < 3; l++) {
						boolean found = false;
						for (int m = 0; m < 6; m++) {
							if ((mask >> m & 1) != 0)
								continue;
							if (words[i].charAt(l) == words[m].charAt(0) &&
								words[j].charAt(l) == words[m].charAt(1) &&
								words[k].charAt(l) == words[m].charAt(2))
							{
								found = true;
								mask += 1 << m;
								break;
							}
						}
						if (!found) {
							good = false;
							break;
						}
					}
					if (good) {
						out.printLine(words[i]);
						out.printLine(words[j]);
						out.printLine(words[k]);
						return;
					}
				}
			}
		}
		out.printLine(0);
	}
}
