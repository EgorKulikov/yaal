package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefsDream {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int maxSegment = in.readInt();
        int[] time = IOUtils.readIntArray(in, count);
        int[] order = ArrayUtils.order(time);
        int answer = 0;
        int lastTime = 0;
        int lastPosition = 0;
        for (int i : order) {
            if (time[i] != lastTime || i - lastPosition >= maxSegment) {
                lastPosition = i;
                lastTime = time[i];
                answer++;
            }
        }
        out.printLine(answer);
	}
}
