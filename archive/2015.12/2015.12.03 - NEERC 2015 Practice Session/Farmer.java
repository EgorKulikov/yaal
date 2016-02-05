package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Farmer {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int answer = in.readInt() * in.readInt();
		int n = in.readInt();
		int[] sx = new int[n];
		int[] sy = new int[n];
		int[] tx = new int[n];
		int[] ty = new int[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			int r = in.readInt();
			sx[i] = x - r;
			sy[i] = y - r;
			tx[i] = x + r;
			ty[i] = y + r;
		}
		boolean[] good = ArrayUtils.createArray(n, true);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!good[j])
					continue;
				for (int k = 0; k < j; k++) {
					if (!good[k])
						continue;
					if (Math.max(sx[j], sx[k]) <= Math.min(tx[j], tx[k]) && Math.max(sy[j], sy[k]) <= Math.min(ty[j], ty[k])) {
						good[k] = false;
						sx[j] = Math.min(sx[j], sx[k]);
						tx[j] = Math.max(tx[j], tx[k]);
						sy[j] = Math.min(sy[j], sy[k]);
						ty[j] = Math.max(ty[j], ty[k]);
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (good[i])
				answer -= (tx[i] - sx[i]) * (ty[i] - sy[i]);
		}
		out.printLine(answer);
	}
}
