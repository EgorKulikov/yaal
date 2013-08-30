package on2013_08.on2013_08_August_Challenge_2013.Save_The_Princess;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SaveThePrincess {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] flags = IOUtils.readIntArray(in, count);
		int[] probability = IOUtils.readIntArray(in, count);
		int sum = (int) ArrayUtils.sumArray(flags);
		double[] result = new double[2 * sum + 1];
		result[sum] = 1;
		double[] next = new double[2 * sum + 1];
		int current = 0;
		for (int i = 0; i < count; i++) {
			int flag = flags[i];
			Arrays.fill(next, sum - current - flag, sum + current + flag + 1, 0);
			double up = probability[i] / 100d;
			double down = 1 - up;
			for (int j = sum - current; j <= sum + current; j++) {
				next[j - flag] += down * result[j];
				next[j + flag] += up * result[j];
			}
			double[] t = result;
			result = next;
			next = t;
			current += flag;
		}
		double answer = 0;
		for (int i = sum; i <= 2 * sum; i++)
			answer += result[i];
		out.printLine(answer);
    }
}
