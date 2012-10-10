package net.egork;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class CreasePainting {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		List<Pair<Long, Long>> path = new ArrayList<Pair<Long, Long>>(count + 1);
		long[] xx = new long[count + 1];
		long[] yy = new long[count + 1];
		for (int i = 0; i < count; i++) {
			char direction = in.readCharacter();
			int distance = in.readInt();
			xx[i + 1] = xx[i];
			yy[i + 1] = yy[i];
			if (direction == 'U')
				yy[i + 1] += distance;
			else if (direction == 'D')
				yy[i + 1] -= distance;
			else if (direction == 'R')
				xx[i + 1] += distance;
			else if (direction == 'L')
				xx[i + 1] -= distance;
		}
		long[] x0 = new long[count];
		long[] y0 = new long[count];
		long[] x1 = new long[count];
		long[] y1 = new long[count];
		for (int i = 0; i < count; i++) {
			x0[i] = Math.min(xx[i], xx[i + 1]);
			y0[i] = Math.min(yy[i], yy[i + 1]);
			x1[i] = Math.max(xx[i], xx[i + 1]);
			y1[i] = Math.max(yy[i], yy[i + 1]);
		}
		boolean[] removed = new boolean[count];
		for (int i = 0; i < count; i++) {
			long answer = x1[i] + y1[i] - x0[i] - y0[i] + 1;
			if (i == 0)
				answer--;
			NavigableSet<Pair<Long, Long>> excluded = new TreeSet<Pair<Long, Long>>();
			for (int j = 0; j < i; j++) {
				if (removed[j])
					continue;
				answer -= tryUnite(x0, y0, x1, y1, i, j);
				answer -= tryUnite(y0, x0, y1, x1, i, j);
				if (x0[i] == x1[i] ^ x0[j] != x1[j])
					continue;
				if (x0[i] == x1[i] && x0[j] <= x0[i] && x1[j] >= x0[i] && y0[i] <= y0[j] && y1[i] >= y0[j]) {
					Pair<Long, Long> point = Pair.makePair(x0[i], y0[j]);
					excluded.add(point);
				} else if (y0[i] == y1[i] && y0[j] <= y0[i] && y1[j] >= y0[i] && x0[i] <= x0[j] && x1[i] >= x0[j]) {
					Pair<Long, Long> point = Pair.makePair(x0[j], y0[i]);
					excluded.add(point);
				}
			}
			for (int j = 0; j < i; j++) {
				if (removed[j])
					continue;
				if (tryUnite(x0, y0, x1, y1, i, j) != 0 || tryUnite(y0, x0, y1, x1, i, j) != 0) {
					removed[j] = true;
					List<Pair<Long, Long>> toRemove = new ArrayList<Pair<Long, Long>>(excluded.subSet(Pair.makePair(x0[j], y0[j]), true, Pair.makePair(x1[j], y1[j]), true));
					excluded.removeAll(toRemove);
				}
			}
			answer -= excluded.size();
			out.printLine(answer);
		}
	}

	private long tryUnite(long[] x0, long[] y0, long[] x1, long[] y1, int i, int j) {
		if (x0[i] == x1[i] && x0[i] == x0[j] && x0[i] == x1[j] && Math.max(y0[i], y0[j]) <= Math.min(y1[i], y1[j])) {
			long result = -Math.max(y0[i], y0[j]) + Math.min(y1[i], y1[j]) + 1;
			long newY0 = Math.min(y0[i], y0[j]);
			long newY1 = Math.max(y1[i], y1[j]);
			y0[i] = newY0;
			y1[i] = newY1;
			return result;
		}
		return 0;
	}
}
