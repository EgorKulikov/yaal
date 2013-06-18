package on2013_06.on2013_06_07_June_Challenge_2013.Dessert_Wizard;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DessertWizard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] delishValue = IOUtils.readIntArray(in, count);
		long[] sum = ArrayUtils.partialSums(delishValue);
		long[] minFromStart = new long[count];
		long[] maxFromStart = new long[count];
		minFromStart[0] = maxFromStart[0] = delishValue[0];
		long curMin = 0;
		long curMax = 0;
		for (int i = 1; i < count; i++) {
			curMin = Math.min(curMin, sum[i]);
			curMax = Math.max(curMax, sum[i]);
			minFromStart[i] = Math.min(minFromStart[i - 1], sum[i + 1] - curMax);
			maxFromStart[i] = Math.max(maxFromStart[i - 1], sum[i + 1] - curMin);
		}
		long min = delishValue[count - 1];
		long max = delishValue[count - 1];
		curMin = sum[count];
		curMax = sum[count];
		long answer = Math.max(maxFromStart[count - 2] - min, max - minFromStart[count - 2]);
		for (int i = count - 2; i > 0; i--) {
			curMin = Math.min(curMin, sum[i + 1]);
			curMax = Math.max(curMax, sum[i + 1]);
			min = Math.min(min, curMin - sum[i]);
			max = Math.max(max, curMax - sum[i]);
			answer = Math.max(answer, Math.max(maxFromStart[i - 1] - min, max - minFromStart[i - 1]));
		}
		out.printLine(answer);
    }
}
