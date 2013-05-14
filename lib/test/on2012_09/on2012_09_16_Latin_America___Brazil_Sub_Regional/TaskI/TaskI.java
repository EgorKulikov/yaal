package on2012_09.on2012_09_16_Latin_America___Brazil_Sub_Regional.TaskI;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int length = in.readInt();
		int count = in.readInt();
		long target = in.readInt() * 2;
		int[] value = new int[length + 1];
		Arrays.fill(value, -1);
		for (int i = 0; i < count; i++) {
			int x = in.readInt();
			int f = in.readInt();
			value[x] = f;
		}
		int[] unknown = new int[length + 1];
		for (int i = length - 1; i > 0; i--) {
			if (value[i] == -1)
				unknown[i] = unknown[i + 1] + 1;
		}
		int[] min = new int[length + 1];
		int[] max = new int[length + 1];
		min[0] = max[0] = value[0];
		for (int i = 1; i <= length; i++)
			min[i] = max[i] = value[i] == -1 ? min[i - 1] : value[i];
		for (int i = length - 1; i >= 0; i--) {
			if (value[i] == -1) {
				min[i] = Math.min(min[i], min[i + 1]);
				max[i] = Math.max(max[i], max[i + 1]);
			}
		}
		boolean[] down = new boolean[length + 1];
		for (int i = 1; i < length; i++) {
			if (value[i] == -1 && value[i - 1] != -1 && value[i - 1] != min[i])
				down[i] = true;
		}
		long sumMin = ArrayUtils.sumArray(min) * 2 - min[0] - min[length];
		long sumMax = ArrayUtils.sumArray(max) * 2 - max[0] - max[length];
		if (target < sumMin || target > sumMax) {
			out.printLine("N");
			return;
		}
		if ((sumMax - target) % 2 != 0) {
			out.printLine("N");
			return;
		}
		out.print("S");
		long delta = (sumMax - target) / 2;
		for (int i = 0; i <= length; i++) {
			if (value[i] != -1)
				continue;
			if (down[i] && delta < unknown[i] * (max[i] - min[i])) {
				long m1 = delta / unknown[i];
				long m1c = unknown[i] - delta % unknown[i];
				for (int j = 0; j < m1c; j++)
					out.print(" " + (max[i] - m1));
				for (int j = 0; j < unknown[i] - m1c; j++)
					out.print(" " + (max[i] - m1 - 1));
				i += unknown[i];
				delta = 0;
				continue;
			}
			long curDelta = Math.min(delta, max[i] - min[i]);
			out.print(" " + (max[i] - curDelta));
			delta -= curDelta;
		}
		out.printLine();
	}
}
