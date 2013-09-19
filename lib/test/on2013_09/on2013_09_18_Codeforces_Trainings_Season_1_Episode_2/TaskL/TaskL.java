package on2013_09.on2013_09_18_Codeforces_Trainings_Season_1_Episode_2.TaskL;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskL {
	int[][] max;
	BigInteger[][] answer;
	int[][] valid;
	int[] permutation;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		permutation = IOUtils.readIntArray(in, count);
		answer = new BigInteger[count][count + 1];
		max = new int[count][count];
		ArrayUtils.fill(max, -1);
		valid = new int[count][count];
		ArrayUtils.fill(valid, -1);
		out.printLine(go(0, count));
    }

	private BigInteger go(int start, int max) {
		if (start == answer.length)
			return BigInteger.ONE;
		max = Math.min(max, answer.length - start);
		if (answer[start][max] != null)
			return answer[start][max];
		if (max == 0)
			return answer[start][max] = BigInteger.ZERO;
		answer[start][max] = go(start, max - 1);
		if (goValid(start, start + max - 1) == 1)
			answer[start][max] = answer[start][max].add(go(start + max, Math.min(max, goMax(start, start + max))));
		return answer[start][max];
	}

	private int goValid(int start, int end) {
		if (valid[start][end] != -1)
			return valid[start][end];
		if (start == end)
			return valid[start][end] = 1;
		if (permutation[end] > permutation[end - 1])
			return valid[start][end] = goValid(start, end - 1);
		return valid[start][end] = 0;
	}

	private int goMax(int first, int second) {
		if (second == answer.length)
			return 0;
		if (max[first][second] != -1)
			return max[first][second];
		if (permutation[first] < permutation[second])
			return max[first][second] = Math.min(1 + goMax(first + 1, second + 1), second - first);
		else
			return max[first][second] = 0;
	}
}
