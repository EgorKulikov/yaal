package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RangeOfData {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] delta = new int[count + 1];
        int modificationCount = in.readInt();
        for (int i = 0; i < modificationCount; i++) {
            int type = in.readInt();
            int from = in.readInt();
            int to = in.readInt();
            int change = in.readInt();
            if (type == 2)
                change = -change;
            delta[from - 1] += change;
            delta[to] -= change;
        }
        int curDelta = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            curDelta += delta[i];
            int curValue = curDelta + i + 1;
            min = Math.min(curValue, min);
            max = Math.max(curValue, max);
        }
        out.printLine(max - min);
	}
}
