package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int friendCount = in.readInt();
		int colorCount = in.readInt();
		int[] days = new int[friendCount];
		for (int i = 0; i < friendCount; i++)
			days[i] = in.readInt();
		int[] colors = new int[colorCount];
		for (int i = 0; i < colorCount; i++)
			colors[i] = in.readInt();
		Arrays.sort(days);
		Arrays.sort(colors);
		int totalIndex = colorCount;
		int shirtIndex = colorCount;
		int socksIndex = colorCount;
		int answer = 0;
		int[] next = new int[colorCount + 1];
		for (int i = 1; i <= colorCount; i++)
			next[i] = i - 1;
		next[0] = -1;
		int[] prev = new int[colorCount + 1];
		for (int i = 0; i < colorCount; i++)
			prev[i] = i + 1;
		prev[colorCount] = -1;
		boolean[] used = new boolean[colorCount + 1];
		for (int i = friendCount - 1; i >= 0; i--) {
			while (used[totalIndex] || totalIndex > 0 && colors[totalIndex - 1] >= 5 * days[i])
				totalIndex = next[totalIndex];
			if (prev[totalIndex] != -1) {
				used[prev[totalIndex]] = true;
				prev[totalIndex] = prev[prev[totalIndex]];
				if (prev[totalIndex] != -1)
					next[prev[totalIndex]] = totalIndex;
				answer++;
				continue;
			}
			while (used[shirtIndex] || shirtIndex > 0 && colors[shirtIndex - 1] >= 3 * days[i])
				shirtIndex = next[shirtIndex];
			if (prev[shirtIndex] == -1) {
				out.printLine("NIE");
				return;
			}
			used[prev[shirtIndex]] = true;
			prev[shirtIndex] = prev[prev[shirtIndex]];
			if (prev[shirtIndex] != -1)
				next[prev[shirtIndex]] = shirtIndex;
			while (used[socksIndex] || socksIndex > 0 && colors[socksIndex - 1] >= 2 * days[i])
				socksIndex = next[socksIndex];
			if (prev[socksIndex] == -1) {
				out.printLine("NIE");
				return;
			}
			used[prev[socksIndex]] = true;
			prev[socksIndex] = prev[prev[socksIndex]];
			if (prev[socksIndex] != -1)
				next[prev[socksIndex]] = socksIndex;
			answer += 2;
		}
		out.printLine(answer);
	}
}
