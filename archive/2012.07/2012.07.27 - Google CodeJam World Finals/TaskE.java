package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] left = new int[count - 1];
		int[] right = new int[count - 1];
		IOUtils.readIntArrays(in, left, right);
		MiscUtils.decreaseByOne(left, right);
		boolean[][] visited = new boolean[count][1 << (count - 1)];
		int index = 0;
		int state = 0;
		int answer = 0;
		while (index != count - 1) {
			if (visited[index][state]) {
				answer = -1;
				break;
			}
			visited[index][state] = true;
			int next;
			if ((state >> index & 1) == 0)
				next = left[index];
			else
				next = right[index];
			state ^= 1 << index;
			index = next;
			answer++;
		}
		out.printLine("Case #" + testNumber + ":", answer == -1 ? "Infinity" : answer);
	}
}
