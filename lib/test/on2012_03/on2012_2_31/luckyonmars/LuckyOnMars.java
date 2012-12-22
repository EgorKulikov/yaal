package on2012_03.on2012_2_31.luckyonmars;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyOnMars {
	int[][] answer = new int[100001][];

	{
		answer[1] = new int[]{0};
		answer[2] = new int[]{0, 1};
		for (int i = 3; i <= 100000; i++) {
			if ((i & (i - 1)) == 0) {
				answer[i] = answer[i - 1];
				continue;
			}
			int last = i - Integer.bitCount(Integer.highestOneBit(i) - 1) - 1;
			boolean found = false;
			for (int j : answer[last]) {
				if (j == last - 1)
					found = true;
			}
			if (found) {
				if (answer[i - 1].length == 2)
					answer[i] = new int[]{i - 1};
				else
					answer[i] = new int[]{answer[i - 1][0], i - 1};
			} else
				answer[i] = answer[i - 1];
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		int[] result = new int[answer[count].length];
		int index = 0;
		for (int i : answer[count])
			result[index++] = permutation[i];
		out.printLine(Array.wrap(result).toArray());
	}
}
