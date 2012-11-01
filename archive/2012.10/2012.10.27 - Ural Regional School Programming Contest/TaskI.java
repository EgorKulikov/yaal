package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int bonus = in.readInt();
		int max = in.readInt();
		int[] answer = new int[count + 1];
		int[] enemy = new int[count + 1];
		for (int i = 1; i < max && i <= count; i++)
			answer[i] = Math.max(i, bonus);
		for (int i = max; i <= count; i++) {
			for (int j = i - 1; j >= i - max; j--) {
				if (answer[i] < enemy[j] + i - j || answer[i] == enemy[j] + i - j && enemy[i] > answer[j]) {
					answer[i] = enemy[j] + i - j;
					enemy[i] = answer[j];
				}
			}
		}
		out.printLine(answer[count], enemy[count]);
	}
}
