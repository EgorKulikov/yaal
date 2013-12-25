package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int headCount = in.readInt();
		int sectorCount = in.readInt();
		long[] heads = IOUtils.readLongArray(in, headCount);
		long[] sectors = IOUtils.readLongArray(in, sectorCount);
		long left = 0;
		long right = sectors[sectorCount - 1] + heads[headCount - 1];
		while (left < right) {
			long middle = (left + right) / 2;
			int i = 0;
			for (long head : heads) {
				if (i == sectorCount)
					break;
				if (head - sectors[i] > middle)
					break;
				long upTo;
				if (sectors[i] > head)
					upTo = head + middle;
				else
					upTo = Math.max(head + middle - 2 * (head - sectors[i]), (middle - (head - sectors[i])) / 2 + head);
				while (i < sectorCount && sectors[i] <= upTo)
					i++;
			}
			if (i == sectorCount)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }
}
