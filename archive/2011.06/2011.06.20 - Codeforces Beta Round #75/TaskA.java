import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] name = in.readString().toCharArray();
		char[] target = in.readString().toCharArray();
		int[][] next = new int[name.length][26];
		Arrays.fill(next[name.length - 1], -1);
		for (int i = name.length - 1; i >= 0; i--)
			next[name.length - 1][name[i] - 'a'] = i;
		for (int i = name.length - 2; i >= 0; i--) {
			System.arraycopy(next[i + 1], 0, next[i], 0, 26);
			next[i][name[i + 1] - 'a'] = i + 1;
		}
		int position = name.length - 1;
		int answer = 0;
		for (char c : target) {
			int nextPosition = next[position][c - 'a'];
			if (nextPosition == -1) {
				answer = -1;
				break;
			}
			if (nextPosition <= position)
				answer++;
			position = nextPosition;
		}
		out.println(answer);
	}
}

