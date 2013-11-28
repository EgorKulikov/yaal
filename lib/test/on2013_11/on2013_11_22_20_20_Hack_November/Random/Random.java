package on2013_11.on2013_11_22_20_20_Hack_November.Random;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Random {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int first = in.readInt();
		int second = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		double alpha = 1 - 2d / count - 2d / count / (count - 1);
		double beta = 2d / count / (count - 1);
		double same = Math.pow(alpha, first) + (Math.pow(alpha, first) - 1) / (alpha - 1) * beta;
		double other = 1 - same;
		double[] expected = new double[count];
		double sum = ArrayUtils.sumArray(numbers);
		for (int i = 0; i < count; i++)
			expected[i] = same * numbers[i] + other * (sum - numbers[i]) / (count - 1);
		double[] pSums = new double[count + 1];
		double[] iSums = new double[count + 1];
		double[] dSums = new double[count + 1];
		int total = count * (count - 1) / 2;
		for (int i = 0; i < second; i++) {
			for (int k = 0; k < count; k++) {
				pSums[k + 1] = pSums[k] + expected[k];
				iSums[k + 1] = iSums[k] + (k + 1) * expected[k];
				dSums[k + 1] = dSums[k] + (count - k) * expected[k];
			}
			for (int k = 0; k < count; k++) {
				int side = Math.min(k + 1, count - k);
				int changed = side * side + (count - 2 * side + 1) * side;
				int notChanged = total - changed;
				expected[k] = (iSums[side] + dSums[count] - dSums[count - side] + (pSums[count - side] - pSums[side]) * side +
					notChanged * expected[k]) * beta;
			}
		}
		double answer = 0;
		for (int i = 0; i < count; i++) {
			answer += expected[i] * beta * ((i + 1) * (count - i) - 1);
		}
		out.printLine(answer);
    }
}
