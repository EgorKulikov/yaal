package on2012_01.on2012_0_7.taske;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int levelCount = in.readInt();
		int colorCount = in.readInt();
		int module = in.readInt();
		long[][] count = new long[5001][];
		for (int i = 0; i <= 5000; i++)
			count[i] = new long[i + 2];
		count[0][0] = 1;
		for (int i = 1; i <= 5000; i++) {
			for (int j = 1; j <= i; j++)
				count[i][j] = (count[i - 1][j - 1] + (j - 1) * count[i - 1][j]) % module;
		}
		long[] c = new long[5001];
		long current = 1;
		for (int i = 1; i <= 5000; i++) {
			current *= colorCount - i + 1;
			current %= module;
			c[i] = current;
		}
		long[] factorial = IntegerUtils.generateFactorial(5001, module);
		int[] length = IOUtils.readIntArray(in, levelCount);
		long[] answer = new long[5001];
		for (int i = 1; i <= length[0]; i++)
			answer[i] = count[length[0]][i];
		long[] next = new long[5001];
		for (int i = 1; i < levelCount; i++) {
			long sum = 0;
			for (int j = 1; j <= length[i - 1]; j++)
				sum = (sum + c[j] * answer[j]) % module;
			for (int j = 1; j <= length[i]; j++) {
				next[j] = sum;
				if (j <= length[i - 1])
					next[j] -= answer[j] * factorial[j] % module;
				next[j] *= count[length[i]][j];
				next[j] %= module;
			}
			long[] temp = next;
			next = answer;
			answer = temp;
		}
		long sum = 0;
		for (int j = 1; j <= length[levelCount - 1]; j++)
			sum = (sum + c[j] * answer[j]) % module;
		if (sum < 0)
			sum += module;
		out.printLine(sum);
	}
}
