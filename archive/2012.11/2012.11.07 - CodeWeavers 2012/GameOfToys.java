package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GameOfToys {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int queryCount = in.readInt();
		int move = in.readInt();
		int[] answer = new int[1001];
		boolean[] present = new boolean[1001];
		for (int i = move; i <= 1000; i++) {
			Arrays.fill(present, false);
			for (int j = 0; 2 * j + move <= i; j++)
				present[answer[j] ^ answer[i - move - j]] = true;
			for (int j = 0; ; j++) {
				if (!present[j]) {
					answer[i] = j;
					break;
				}
			}
		}
		for (int i = 0; i < queryCount; i++) {
			if (answer[in.readInt()] == 0)
				out.printLine("Clumsy");
			else
				out.printLine("Brainy");
		}
	}
}
