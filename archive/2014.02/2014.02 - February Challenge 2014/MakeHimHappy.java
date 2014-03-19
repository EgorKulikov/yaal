package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MakeHimHappy {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] types = IOUtils.readIntArray(in, count);
		IntHashSet set = new IntHashSet();
		for (int i = 0, j = count - 1; i <= j; i++, j--) {
			if (set.contains(types[i]) || set.contains(types[j]) || types[i] != types[j] && types[i] + types[j] == required) {
				out.printLine(i + 1);
				return;
			}
			if (2 * types[i] != required)
				set.add(required - types[i]);
			if (2 * types[j] != required)
				set.add(required - types[j]);
		}
		out.printLine(-1);
    }
}
