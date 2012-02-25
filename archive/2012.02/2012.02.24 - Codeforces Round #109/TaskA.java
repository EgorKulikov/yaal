package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] string = in.readString().toCharArray();
		int count = in.readInt();
		boolean[][] forbidden = new boolean[256][256];
		for (int i = 0; i < count; i++) {
			char[] pair = in.readString().toCharArray();
			forbidden[pair[0]][pair[1]] = true;
			forbidden[pair[1]][pair[0]] = true;
		}
		int changeCount = 0;
		for (int i = 1; i < string.length; i++) {
			if (string[i] != string[i - 1])
				changeCount++;
		}
		char[] ch = new char[changeCount + 1];
		int[] cnt = new int[changeCount + 1];
		ch[0] = string[0];
		cnt[0] = 1;
		int index = 0;
		for (int i = 1; i < string.length; i++) {
			if (string[i] == string[i - 1])
				cnt[index]++;
			else {
				index++;
				ch[index] = string[i];
				cnt[index] = 1;
			}
		}
		int[] sum = new int[2];
		index = 0;
		int answer = 0;
		char current = 0;
		for (int i = 0; i <= changeCount; i++) {
			if (!forbidden[current][ch[i]]) {
				answer += Math.min(sum[0], sum[1]);
				sum[0] = sum[1] = 0;
			}
			sum[index] += cnt[i];
			index = 1 - index;
			current = ch[i];
		}
		answer += Math.min(sum[0], sum[1]);
		out.printLine(answer);
	}
}
