package on2014_10.on2014_10_19_Codeforces_Round__274__Div__1_.D___Parcels;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	int[] answer;
	int[] key;
	int curKey;
	int[][] result;
	int[] inTime;
	int[] outTime;
	int[] weight;
	int[] sturdiness;
	int[] value;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int baseSturdiness = in.readInt();
		inTime = new int[count];
		outTime = new int[count];
		weight = new int[count];
		sturdiness = new int[count];
		value = new int[count];
		IOUtils.readIntArrays(in, inTime, outTime, weight, sturdiness, value);
		inTime = Arrays.copyOf(inTime, count + 1);
		outTime = Arrays.copyOf(outTime, count + 1);
		weight = Arrays.copyOf(weight, count + 1);
		sturdiness = Arrays.copyOf(sturdiness, count + 1);
		value = Arrays.copyOf(value, count + 1);
		outTime[count] = 2 * count;
		sturdiness[count] = baseSturdiness;
		int[] order = ArrayUtils.createOrder(count + 1);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (outTime[first] != outTime[second]) {
					return outTime[second] - outTime[first];
				}
				return inTime[first] - inTime[second];
			}
		});
		ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), inTime, outTime, weight, sturdiness, value);
		result = new int[count + 1][baseSturdiness + 1];
		ArrayUtils.fill(result, -1);
		answer = new int[2 * count + 1];
		key = new int[2 * count + 1];
		out.printLine(go(0, baseSturdiness));
    }

	private int go(int current, int remaining) {
		if (result[current][remaining] != -1) {
			return result[current][remaining];
		}
		for (int i = current + 1; i < inTime.length; i++) {
			if (outTime[i] <= inTime[current]) {
				break;
			}
			if (inTime[i] >= inTime[current] && weight[i] <= remaining) {
				go(i, Math.min(remaining - weight[i], sturdiness[i]));
			}
		}
		curKey++;
		answer[outTime[current]] = 0;
		key[outTime[current]] = curKey;
		int time = outTime[current];
		for (int i = current + 1; i < inTime.length; i++) {
			if (outTime[i] <= inTime[current]) {
				break;
			}
			while (time > outTime[i]) {
				if (key[time - 1] != curKey) {
					answer[time - 1] = answer[time];
					key[time - 1] = curKey;
				} else {
					answer[time - 1] = Math.max(answer[time - 1], answer[time]);
				}
				time--;
			}
			if (inTime[i] >= inTime[current] && weight[i] <= remaining) {
				int result = value[i] + go(i, Math.min(remaining - weight[i], sturdiness[i]));
				if (key[inTime[i]] != curKey) {
					answer[inTime[i]] = 0;
					key[inTime[i]] = curKey;
				}
				answer[inTime[i]] = Math.max(answer[inTime[i]], result + answer[time]);
			}
		}
		result[current][remaining] = answer[time];
		for (int i = inTime[current]; i < time; i++) {
			if (key[i] == curKey) {
				result[current][remaining] = Math.max(result[current][remaining], answer[i]);
			}
		}
		return result[current][remaining];
	}
}
