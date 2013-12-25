package on2013_10.on2013_10_16_Codeforces_Trainings_Season_1_Episode_6.TaskF;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] required = new int[count];
		int[] price = new int[count];
		IOUtils.readIntArrays(in, required, price);
		long[] sums = ArrayUtils.partialSums(required);
		long[] answer = new long[count + 1];
		for (int i = 1; i <= count; i++) {
			answer[i] = Long.MAX_VALUE;
			for (int j = i - 1; j >= 0; j--)
				answer[i] = Math.min(answer[i], answer[j] + (sums[i] - sums[j] + 10) * price[i - 1]);
		}
		out.printLine(answer[count]);
    }
}
