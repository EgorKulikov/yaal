package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;

public class AntsMeet {
	public int countAnts(int[] x, int[] y, String direction) {
		int[] dx = new int[256];
		int[] dy = new int[256];
		dy['N'] = 1;
		dy['S'] = -1;
		dx['W'] = -1;
		dx['E'] = 1;
		boolean[] killed = new boolean[x.length];
		for (int i = 0; i < x.length; i++) {
			x[i] *= 2;
			y[i] *= 2;
		}
		for (int i = 0; i < 4000; i++) {
			for (int j = 0; j < x.length; j++) {
				if (killed[j])
					continue;
				x[j] += dx[direction.charAt(j)];
				y[j] += dy[direction.charAt(j)];
			}
			for (int j = 0; j < x.length; j++) {
				if (killed[j])
					continue;
				for (int k = j + 1; k < x.length; k++) {
					if (killed[k])
						continue;
					if (x[j] == x[k] && y[j] == y[k])
						killed[j] = killed[k] = true;
				}
			}
		}
		return CollectionUtils.count(Array.wrap(killed), false);
	}

}

