package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int subNetCount = in.readInt();
		String[] ips = IOUtils.readStringArray(in, count);
		int[] masks = new int[count];
		for (int i = 0; i < count; i++)
			masks[i] = MiscUtils.parseIP(ips[i]);
		for (int i = 31; i > 0; i--) {
			int current = -(1 << i);
			Set<Integer> set = new EHashSet<Integer>();
			for (int j : masks)
				set.add(current & j);
			if (set.size() == subNetCount) {
				out.printLine(MiscUtils.buildIP(current));
				return;
			}
		}
		out.printLine(-1);
	}
}
