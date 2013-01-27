package on2011_10.on2011_9_27.taskd1;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long[] happy = IntegerUtils.generateHappy(18);
		int count = in.readInt();
		long moveCount = in.readLong();
		long[] from = new long[count];
		long[] to = new long[count];
		IOUtils.readLongArrays(in, from, to);
		long[] leftSorted = from.clone();
		Arrays.sort(leftSorted);
		long[] rightSorted = to.clone();
		Arrays.sort(rightSorted);
		long maxLength = Long.MAX_VALUE;
		for (int i = 0; i < count; i++)
			maxLength = Math.min(maxLength, to[i] - from[i]);
		double[] leftApproximateSums = new double[count + 1];
		long[] leftExactSums = new long[count + 1];
		for (int i = count - 1; i >= 0; i--) {
			leftApproximateSums[i] = leftApproximateSums[i + 1] + leftSorted[i];
			leftExactSums[i] = leftExactSums[i + 1] + leftSorted[i];
		}
		double[] rightApproximateSums = new double[count + 1];
		long[] rightExactSums = new long[count + 1];
		for (int i = 1; i <= count; i++) {
			rightApproximateSums[i] = rightApproximateSums[i - 1] + rightSorted[i - 1];
			rightExactSums[i] = rightExactSums[i - 1] + rightSorted[i - 1];
		}
		double maxApproximateDiff = (double)moveCount + Long.MAX_VALUE;
		int leftIndex = 0;
		int rightIndex = 0;
		int answer = 0;
		for (int i = 0; i + answer < happy.length; i++) {
			while (leftIndex < count && leftSorted[leftIndex] <= happy[i])
				leftIndex++;
			for (int j = i + answer; j < happy.length; j++) {
				if (happy[j] - happy[i] > maxLength)
					break;
				while (rightIndex < count && rightSorted[rightIndex] < happy[j])
					rightIndex++;
				double approximate = leftApproximateSums[leftIndex] - (double)(count - leftIndex) * happy[i] +
					(double)rightIndex * happy[j] - rightApproximateSums[rightIndex];
				if (approximate > maxApproximateDiff)
					break;
				long exact = leftExactSums[leftIndex] - (count - leftIndex) * happy[i] + rightIndex * happy[j] -
					rightExactSums[rightIndex];
				if (moveCount - exact >= 0)
					answer++;
				else
					break;
			}
		}
		out.println(answer);
	}
}
