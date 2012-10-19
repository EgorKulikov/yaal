package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int artistCount = in.readInt();
		int favoriteCount = in.readInt();
		boolean[] isFavorite = new boolean[artistCount];
		for (int i = 0; i < favoriteCount; i++)
			isFavorite[in.readInt() - 1] = true;
		int count = in.readInt();
		int[] min = new int[count];
		int[] max = new int[count];
		for (int i = 0; i < count; i++) {
			in.readString();
			int curCount = in.readInt();
			int unknown = 0;
			int good = 0;
			for (int j = 0; j < curCount; j++) {
				int id = in.readInt() - 1;
				if (id == -1)
					unknown++;
				else if (isFavorite[id])
					good++;
			}
			int delta = Math.max(0, favoriteCount - artistCount + curCount - good);
			good += delta;
			unknown -= delta;
			min[i] = good;
			max[i] = Math.min(good + unknown, favoriteCount);
		}
		for (int i = 0; i < count; i++) {
			boolean canBeGood = true;
			boolean mustBeGood = true;
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				if (min[i] < max[j])
					mustBeGood = false;
				if (max[i] < min[j])
					canBeGood = false;
			}
			if (mustBeGood)
				out.printLine(0);
			else if (canBeGood)
				out.printLine(2);
			else
				out.printLine(1);
		}
	}
}
