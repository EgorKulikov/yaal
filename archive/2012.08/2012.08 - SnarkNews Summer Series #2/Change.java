package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class Change {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int bankCount = in.readInt();
        int[] delta = IOUtils.readIntArray(in, count - 1);
        long[] value = new long[count];
        value[0] = 1;
        for (int i = 1; i < count; i++)
            value[i] = value[i - 1] * delta[count - i - 1];
        long[] cost = new long[bankCount];
        for (int i = 0; i < bankCount; i++) {
            for (int j = 0; j < count; j++)
                cost[i] += in.readLong() * value[count - j - 1];
        }
        List<Long> wrapped = Array.wrap(cost);
        out.printLine(CollectionUtils.maxElement(wrapped) - CollectionUtils.minElement(wrapped));
	}
}
