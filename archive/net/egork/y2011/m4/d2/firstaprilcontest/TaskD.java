package net.egork.y2011.m4.d2.firstaprilcontest;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		String[] firstTeam = new String[n];
		String[] secondTeam = new String[n];
		int[] length = new int[n];
		int[] last = new int[n];
		Map<String, Integer> bestWin = new HashMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			String[] match = in.readString().split("-");
			firstTeam[i] = match[0];
			secondTeam[i] = match[1];
			if (bestWin.containsKey(secondTeam[i])) {
				int lastIndex = bestWin.get(secondTeam[i]);
				length[i] = 1 + length[lastIndex];
				last[i] = lastIndex;
			} else {
				length[i] = 1;
				last[i] = -1;
			}
			if (!bestWin.containsKey(firstTeam[i]) || length[bestWin.get(firstTeam[i])] < length[i])
				bestWin.put(firstTeam[i], i);
		}
		int max = 0;
		int index = -1;
		for (int i = 0; i < n; i++) {
			if (max < length[i]) {
				max = length[i];
				index = i;
			}
		}
		out.println(max);
		out.print(firstTeam[index]);
		for (int i = 0; i < max; i++) {
			out.print("-" + secondTeam[index]);
			index = last[index];
		}
		out.println();
	}
}

