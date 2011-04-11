package net.egork.timus;

import net.egork.arrays.ArrayWrapper;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1208 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int teamCount = in.readInt();
		String[][] teams = in.readStringTable(teamCount, 3);
		int[] conflicts = new int[teamCount];
		for (int i = 0; i < teamCount; i++) {
			for (int j = 0; j < teamCount; j++) {
				if (i == j)
					continue;
				boolean conflict = false;
				for (String participant : teams[i]) {
					if (ArrayWrapper.wrap(teams[j]).contains(participant))
						conflict = true;
				}
				if (conflict)
					conflicts[i] += 1 << j;
			}
		}
		int answer = 0;
		for (int i = 0; i < (1 << teamCount); i++) {
			boolean valid = true;
			for (int j = 0; valid && j < teamCount; j++) {
				if (((i >> j) & 1) == 1 && (i & conflicts[j]) != 0)
					valid = false;
			}
			if (valid)
				answer = Math.max(answer, Integer.bitCount(i));
		}
		out.println(answer);
	}
}

