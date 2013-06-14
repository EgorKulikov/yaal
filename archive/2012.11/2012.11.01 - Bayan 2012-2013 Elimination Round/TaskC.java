package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int hLeft = in.readInt();
		int hRight = in.readInt();
		int count = in.readInt();
		int[] score = new int[count];
		boolean[] side = new boolean[count];
		int[] from = new int[count];
		int[] to = new int[count];
		for (int i = 0; i < count; i++) {
			score[i] = in.readInt();
			side[i] = in.readCharacter() == 'T';
			from[i] = in.readInt() * 2;
			to[i] = in.readInt() * 2;
		}
		int answer = 0;
		for (int a = 0; a < 2; a++) {
			for (int i = 1; i <= count; i++) {
				boolean[] hit = new boolean[count];
				int curHRight = i % 2 == 0 ? hRight + 100 * i : 100 * (i + 1) - hRight;
				boolean good = true;
				int total = 0;
				for (int j = 0; j < i; j++) {
					boolean curSide = j % 2 == 0;
					long h1 = 100 * (j + 1) - hLeft;
					long h2 = curHRight - 100 * (j + 1);
					long position = h1 * 100000 / (h1 + h2);
					if (position * (h1 + h2) == h1 * 100000)
						position *= 2;
					else
						position = position * 2 + 1;
					boolean found = false;
					for (int k = 0; k < count; k++) {
						if (side[k] == curSide && from[k] <= position && to[k] >= position) {
							if (hit[k])
								break;
							hit[k] = true;
							found = true;
							total += score[k];
							break;
						}
					}
					if (!found) {
						good = false;
						break;
					}
				}
				if (good)
					answer = Math.max(answer, total);
			}
			hLeft = 100 - hLeft;
			hRight = 100 - hRight;
			for (int i = 0; i < count; i++)
				side[i] = !side[i];
		}
		out.printLine(answer);
	}
}
