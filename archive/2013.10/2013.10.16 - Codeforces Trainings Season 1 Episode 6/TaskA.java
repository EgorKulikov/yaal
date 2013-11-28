package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] denominations = IOUtils.readIntArray(in, 6);
		int[] result = new int[3001];
		Arrays.fill(result, Integer.MAX_VALUE / 5);
		result[0] = 0;
		for (int i : denominations) {
			for (int j = i; j <= 3000; j++)
				result[j] = Math.min(result[j], result[j - i] + 1);
		}
		int[] answer = new int[101];
		Arrays.fill(answer, Integer.MAX_VALUE / 5);
		for (int i = 0; i <= 3000; i++) {
			for (int j = Math.max(0, i - 100); j <= i; j++)
				answer[i - j] = Math.min(answer[i - j], result[i] + result[j]);
		}
		out.printFormat("%.2f %d\n", ArrayUtils.sumArray(answer) / 100d, ArrayUtils.maxElement(answer));
    }
}
