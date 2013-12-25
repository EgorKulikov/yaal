package on2012_05.on2012_4_1.median;



import net.egork.misc.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Median {
	private int[][] answer;
	int[] countMax;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int maximum = CollectionUtils.maxElement(Array.wrap(array));
		answer = new int[count][count];
		countMax = new int[count + 1];
		for (int i = 0; i < count; i++) {
			countMax[i + 1] = countMax[i];
			if (array[i] == maximum)
				countMax[i + 1]++;
		}
		ArrayUtils.fill(answer, -1);
		out.printLine(go(0, count - 1));
	}

	private int go(int from, int to) {
		if (answer[from][to] != -1)
			return answer[from][to];
		if (countMax[to + 1] - countMax[from] == to - from + 1)
			return answer[from][to] = 0;
		if ((countMax[to + 1] - countMax[from]) * 2 >= to - from + 1)
			return answer[from][to] = 1;
		answer[from][to] = Integer.MAX_VALUE / 2;
		if (countMax[to + 1] - countMax[from] == 0)
			return answer[from][to];
		for (int i = from; i <= to; i++) {
			for (int j = i; j <= to; j++) {
				if ((j - i + 1 + countMax[to + 1] - countMax[j + 1] + countMax[i] - countMax[from]) * 2 >= to - from + 1)
					answer[from][to] = Math.min(answer[from][to], go(i, j) + 1);
			}
		}
		return answer[from][to];
	}
}
