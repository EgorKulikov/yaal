package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int errors = in.readInt();
		char[][] words = IOUtils.readTable(in, 3, length);
		int different = 0;
		int[] bad = new int[3];
		for (int i = 0; i < length; i++) {
			if (words[0][i] == words[1][i] && words[0][i] == words[2][i])
				continue;
			if (words[0][i] == words[1][i])
				bad[2]++;
			else if (words[0][i] == words[2][i])
				bad[1]++;
			else if (words[1][i] == words[2][i])
				bad[0]++;
			else
				different++;
		}
		int[] remaining = new int[3];
		Arrays.fill(remaining, errors);
		if (!can(remaining, bad, different)) {
			out.printLine(-1);
			return;
		}
		char[] answer = new char[length];
		char[] candidates = new char[4];
		for (int i = 0; i < length; i++) {
			if (!(words[0][i] == words[1][i] && words[0][i] == words[2][i])) {
				if (words[0][i] == words[1][i])
					bad[2]--;
				else if (words[0][i] == words[2][i])
					bad[1]--;
				else if (words[1][i] == words[2][i])
					bad[0]--;
				else
					different--;
			}
			candidates[0] = 'A';
			for (int j = 0; j < 3; j++)
				candidates[j + 1] = words[j][i];
			Arrays.sort(candidates);
			for (char c : candidates) {
				for (int j = 0; j < 3; j++) {
					if (c != words[j][i])
						remaining[j]--;
				}
				if (can(remaining, bad, different)) {
					answer[i] = c;
					break;
				}
				for (int j = 0; j < 3; j++) {
					if (c != words[j][i])
						remaining[j]++;
				}
			}
		}
		out.printLine(answer);
	}

	private boolean can(int[] remaining, int[] bad, int different) {
		int more = 0;
		int diff = 0;
		int min = 0;
		for (int i = 0; i < 3; i++) {
			if (remaining[i] < 0)
				return false;
			if (remaining[i] < bad[i]) {
				more++;
				min = bad[i] - remaining[i];
			}
			diff += remaining[i] - bad[i];
		}
		if (more > 1)
			return false;
		if (more == 1) {
			for (int i = 0; i < 3; i++) {
				if (remaining[i] >= bad[i] && remaining[i] - bad[i] < min + different)
					return false;
			}
			return true;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 3; j++) {
				if (remaining[i] + remaining[j] - bad[i] - bad[j] < different)
					return false;
			}
		}
		return diff >= 2 * different;
	}
}
