package on2014_01.on2014_01_12_Codeforces_Round__223__Div__1_.E___Sereja_and_Dividing;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	static final int BUBEN = 25;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
//		Random random = new Random(239);
		int[] array = new int[count];//IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++) {
			array[i] = in.readInt();
//			array[i] = random.nextInt(100000) + 1;
		}
		int[] order = ArrayUtils.order(array);
		int[] next = new int[count];
		int[] last = new int[count];
		for (int i = 0; i < count; i++) {
			next[i] = i + 1;
			last[i] = i - 1;
		}
		double answer = 0;
		long[] left = new long[BUBEN];
		long[] right = new long[BUBEN];
		double[] two = new double[2 * BUBEN];
		long[] qty = new long[BUBEN * 2];
		two[0] = 1;
		for (int i = 1; i < two.length; i++)
			two[i] = two[i - 1] * 2;
		for (int i : order) {
			left[0] = i;
			int leftSize = 1;
			for (int j = 1; j < BUBEN; j++) {
				left[j] = last[((int) left[j - 1])];
				leftSize++;
				if (left[j] == -1)
					break;
			}
			right[0] = i;
			int rightSize = 1;
			for (int j = 1; j < BUBEN; j++) {
				right[j] = next[((int) right[j - 1])];
				rightSize++;
				if (right[j] == count)
					break;
			}
			Arrays.fill(qty, 0);
			for (int j = 1; j < leftSize; j++) {
				for (int k = 1; k < rightSize; k++)
//					answer += array[i] / two[j + k - 2] * (left[j - 1] - left[j]) * (right[k] - right[k - 1]);
					qty[j + k - 2] += (left[j - 1] - left[j]) * (right[k] - right[k - 1]);
			}
			for (int j = 0; j < leftSize - 3 + rightSize; j++)
				answer += array[i] / two[j] * qty[j];
			if (last[i] != -1)
				next[last[i]] = next[i];
			if (next[i] != count)
				last[next[i]] = last[i];
		}
		answer /= count;
		answer /= count;
		out.printLine(answer / 2);
    }
}
