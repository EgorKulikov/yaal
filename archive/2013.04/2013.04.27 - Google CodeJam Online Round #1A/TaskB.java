package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int energy = in.readInt();
		int recharge = in.readInt();
		recharge = Math.min(recharge, energy);
		int count = in.readInt();
		long[] value = IOUtils.readLongArray(in, count);
		int[] fromLeft = new int[count];
		int[] toRight = new int[count];
		int[] spent = new int[count];
		int[] order = ArrayUtils.order(value);
		ArrayUtils.reverse(order);
		Arrays.fill(fromLeft, energy);
		for (int i : order) {
			if (fromLeft[i] < toRight[i])
				throw new RuntimeException();
			spent[i] = fromLeft[i] - toRight[i];
			int passed = toRight[i];
			for (int j = i + 1; j < count; j++) {
				passed += recharge;
				if (passed >= fromLeft[j] || spent[j] != 0)
					break;
				fromLeft[j] = passed;
			}
			int required = fromLeft[i];
			for (int j = i - 1; j >= 0; j--) {
				required -= recharge;
				if (required <= toRight[j] || spent[j] != 0)
					break;
				toRight[j] = required;
			}
		}
		long answer = 0;
		for (int i = 0; i < count; i++)
			answer += spent[i] * value[i];
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
