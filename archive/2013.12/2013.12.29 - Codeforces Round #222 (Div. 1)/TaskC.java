package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	int[] skills;
	char[] type;
	int[] team;

	int[][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int heroCount = in.readInt();
		skills = IOUtils.readIntArray(in, heroCount);
		ArrayUtils.sort(skills, IntComparator.REVERSE);
		int count = in.readInt();
		skills = Arrays.copyOf(skills, count);
		team = new int[count];
		type = new char[count];
		for (int i = 0; i < count; i++) {
			type[i] = in.readCharacter();
			team[i] = in.readInt();
		}
		answer = new int[count][1 << count];
		ArrayUtils.fill(answer, Integer.MIN_VALUE);
		for (int i = count - 1; i >= 0; i--) {
			for (int j = 0; j < (1 << count); j++) {
				if (Integer.bitCount(j) <= i)
					solve(i, j);
			}
		}
		out.printLine(solve(0, 0));
    }

	private int solve(int step, int mask) {
		if (step == skills.length)
			return 0;
		if (answer[step][mask] != Integer.MIN_VALUE)
			return answer[step][mask];
		if (type[step] == 'p')
			answer[step][mask] = pick(step, mask);
		else
			answer[step][mask] = ban(step, mask);
		if (team[step] == 2)
			answer[step][mask] = -answer[step][mask];
		if (step > 0 && team[step - 1] == 2)
			answer[step][mask] = -answer[step][mask];
		return answer[step][mask];
	}

	private int pick(int step, int mask) {
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < skills.length; i++) {
			if ((mask >> i & 1) == 0)
				result = Math.max(result, skills[i] + solve(step + 1, mask + (1 << i)));
		}
		return result;
	}

	private int ban(int step, int mask) {
		int result = solve(step + 1, mask);
		for (int i = 0; i < skills.length; i++) {
			if ((mask >> i & 1) == 0)
				result = Math.max(result, solve(step + 1, mask + (1 << i)));
		}
		return result;
	}
}
