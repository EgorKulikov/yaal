package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class BlockGame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[][] time = IOUtils.readIntTable(in, count, count);
        int[][] order = new int[count][];
        for (int i = 0; i < count; i++)
            order[i] = ArrayUtils.order(time[i]);
        long[] max = new long[count];
        Arrays.fill(max, Long.MIN_VALUE);
        int[] index = new int[count];
        while (true) {
            boolean updated = false;
            for (int i = 0; i < count; i++)
                max[order[i][index[i]]] = Math.max(max[order[i][index[i]]], time[i][order[i][index[i]]]);
            for (int i = 0; i < count; i++) {
                if (max[order[i][index[i]]] != time[i][order[i][index[i]]]) {
                    updated = true;
                    index[i]++;
                    if (index[i] == count) {
                        out.printLine(-1);
                        return;
                    }
                }
            }
            if (!updated)
                break;
        }
        int[] answer = new int[count];
        for (int i = 0; i < count; i++)
            answer[i] = order[i][index[i]] + 1;
        out.printLine(Array.wrap(answer).toArray());
	}
}
