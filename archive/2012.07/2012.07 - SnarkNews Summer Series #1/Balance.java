package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Balance {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        if (count == 1)
            throw new UnknownError();
        int[] array = IOUtils.readIntArray(in, count);
        int[] startMin = new int[count];
        int[] startMax = new int[count];
        startMin[0] = startMax[0] = array[0];
        for (int i = 1; i < count; i++) {
            startMin[i] = Math.min(startMin[i - 1], array[i]);
            startMax[i] = Math.max(startMax[i - 1], array[i]);
        }
        int[] endMin = new int[count];
        int[] endMax = new int[count];
        endMin[count - 1] = endMax[count - 1] = array[count - 1];
        for (int i = count - 2; i >= 0; i--) {
            endMin[i] = Math.min(endMin[i + 1], array[i]);
            endMax[i] = Math.max(endMax[i + 1], array[i]);
        }
        int answer = 1;
        int value = endMax[1] - endMin[1];
        for (int i = 2; i < count; i++) {
            int current = Math.max(startMax[i - 1] - startMin[i - 1], endMax[i] - endMin[i]);
            if (current < value) {
                value = current;
                answer = i;
            }
        }
        out.printLine(answer);
    }
}
