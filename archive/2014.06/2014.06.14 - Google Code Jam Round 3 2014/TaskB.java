package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int dianaHit = in.readInt();
		int towerHit = in.readInt();
		int count = in.readInt();
		int[] health = new int[count];
		int[] gold = new int[count];
		IOUtils.readIntArrays(in, health, gold);
		int[][] answer = new int[count + 1][2000];
		ArrayUtils.fill(answer, Integer.MIN_VALUE);
		answer[0][1] = 0;
		for (int i = 0; i < count; i++) {
			int towerKill = (health[i] + towerHit - 1) / towerHit;
			for (int j = 0; j < 2000; j++) {
				if (answer[i][j] == Integer.MIN_VALUE)
					continue;
				for (int k = 0; k < towerKill; k++) {
					int remaining = health[i] - k * towerHit;
					int dianaKill = (remaining + dianaHit - 1) / dianaHit;
					if (dianaKill <= k + j)
						answer[i + 1][k + j - dianaKill] = Math.max(answer[i + 1][k + j - dianaKill], answer[i][j] + gold[i]);
				}
				answer[i + 1][j + towerKill] = Math.max(answer[i + 1][j + towerKill], answer[i][j]);
			}
		}
		out.printLine("Case #" + testNumber + ":", ArrayUtils.maxElement(answer[count]));
    }
}
