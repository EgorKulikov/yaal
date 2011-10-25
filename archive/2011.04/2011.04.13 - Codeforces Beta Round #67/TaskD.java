package April2011.CodeforcesBetaRound67;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int arrayCount = in.readInt();
		int bigArrayLength = in.readInt();
		int[][] arrays = new int[arrayCount][];
		for (int i = 0; i < arrayCount; i++) {
			int length = in.readInt();
			arrays[i] = in.readIntArray(length);
		}
		int[] bigArray = in.readIntArray(bigArrayLength);
		int[] maximum = new int[arrayCount];
		int[] minimum = new int[arrayCount];
		int[] maxDif = new int[arrayCount];
		int[] sum = new int[arrayCount];
		for (int i = 0; i < arrayCount; i++) {
			maximum[i] = Integer.MIN_VALUE;
			minimum[i] = 0;
			maxDif[i] = Integer.MIN_VALUE;
			sum[i] = 0;
			for (int value : arrays[i]) {
				sum[i] += value;
				maximum[i] = Math.max(maximum[i], sum[i]);
				maxDif[i] = Math.max(maxDif[i], sum[i] - minimum[i]);
				minimum[i] = Math.min(minimum[i], sum[i]);
			}
		}
		long result = Long.MIN_VALUE;
		long currentMaximum = Long.MIN_VALUE;
		long currentMinimum = 0;
		long currentSum = 0;
		for (int index : bigArray) {
			index--;
			result = Math.max(result, maxDif[index]);
			result = Math.max(result, maximum[index] + currentSum - currentMinimum);
			currentMaximum = Math.max(currentMaximum, currentSum + maximum[index]);
			currentMinimum = Math.min(currentMinimum, currentSum + minimum[index]);
			currentSum += sum[index];
		}
		out.println(result);
	}
}

