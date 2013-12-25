package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Eve {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int initialCount = in.readInt();
		for (int i = 0; i < initialCount; i++)
			in.readCharacter();
		int eventCount = in.readInt();
		int[] id = new int[eventCount];
		int birthCount = 0;
		for (int i = 0; i < eventCount; i++) {
			id[i] = in.readInt();
			if (id[i] < 0)
				continue;
			id[i] = in.readInt() - 1;
			in.readCharacter();
			birthCount++;
		}
		boolean[] alive = new boolean[initialCount + birthCount];
		Arrays.fill(alive, true);
		for (int i : id) {
			if (i < 0)
				alive[-i - 1] = false;
		}
		int[] parent = new int[initialCount + birthCount];
		for (int i = 0; i < initialCount; i++)
			parent[i] = i;
		int index = initialCount;
		for (int i : id) {
			if (i >= 0)
				parent[index++] = parent[i];
		}
		boolean[] isAliveFamily = new boolean[initialCount];
		for (int i = 0; i < initialCount + birthCount; i++) {
			if (alive[i])
				isAliveFamily[parent[i]] = true;
		}
		int[] dna = new int[initialCount];
		for (int i = 0; i < initialCount; i++)
			dna[i] = -i;
		int measureCount = in.readInt();
		for (int i = 0; i < measureCount; i++) {
			int curID = in.readInt() - 1;
			dna[parent[curID]] = in.readInt();
		}
		Set<Integer> dnas = new HashSet<Integer>();
		for (int i = 0; i < initialCount; i++) {
			if (isAliveFamily[i])
				dnas.add(dna[i]);
		}
		if (dnas.size() == 1) {
			out.printLine("YES");
			return;
		}
		int positiveCount = 0;
		for (int i : dnas) {
			if (i > 0)
				positiveCount++;
		}
		if (positiveCount <= 1)
			out.printLine("POSSIBLY");
		else
			out.printLine("NO");
	}
}
