package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskN {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == -1)
			throw new UnknownError();
		String[] name = new String[count];
		int[] volume = new int[count];
		for (int i = 0; i < count; i++) {
			volume[i] = in.readInt() * in.readInt() * in.readInt();
			name[i] = in.readString();
		}
		long mean = ArrayUtils.sumArray(volume) / count;
		String robber = null;
		String victim = null;
		for (int i = 0; i < count; i++) {
			if (volume[i] > mean)
				robber = name[i];
			else if (volume[i] < mean)
				victim = name[i];
		}
		out.printLine(robber, victim);
	}
}
