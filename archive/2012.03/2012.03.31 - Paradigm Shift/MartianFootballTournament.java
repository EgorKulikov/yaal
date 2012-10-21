package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MartianFootballTournament {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] result = new int[count];
		Arrays.sort(array);
		int from = 0;
		int to = count - 1;
		for (int i = 1; i < count - 1; i += 2)
			result[i] = array[from++];
		for (int i = 2; i < count - 1; i += 2)
			result[i] = array[to--];
		result[0] = array[to];
		result[count - 1] = array[from];
		long answer = 0;
		for (int i = 1; i < count; i++)
			answer += Math.abs(result[i] - result[i - 1]);
		from = 0;
		to = count - 1;
		for (int i = 2; i < count - 1; i += 2)
			result[i] = array[from++];
		for (int i = 1; i < count - 1; i += 2)
			result[i] = array[to--];
		result[0] = array[from];
		result[count - 1] = array[to];
		long otherAnswer = 0;
		for (int i = 1; i < count; i++)
			otherAnswer += Math.abs(result[i] - result[i - 1]);
		answer = Math.max(answer, otherAnswer);
		out.printLine(answer);
	}
}
