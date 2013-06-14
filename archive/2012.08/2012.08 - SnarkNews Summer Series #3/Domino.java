package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Domino {
    int[] from, to;
    int[] previousSame;
    long[][][] result;
    int count;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
        count = in.readInt();
        from = new int[count];
        to = new int[count];
        IOUtils.readIntArrays(in, from, to);
        previousSame = new int[count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                if (from[i] == from[j] && to[i] == to[j] || from[i] == to[j] && to[i] == from[j])
                    previousSame[i] += 1 << j;
            }
        }
        result = new long[8][8][1 << count];
        ArrayUtils.fill(result, -1);
        long answer = 0;
        for (int i = 0; i < count; i++) {
            if (previousSame[i] == 0) {
                answer += go(from[i], to[i], (1 << count) - 1 - (1 << i));
                if (from[i] != to[i])
                    answer += go(to[i], from[i], (1 << count) - 1 - (1 << i));
            }
        }
        out.printLine(answer);
	}

    private long go(int start, int end, int mask) {
        if (result[start][end][mask] != -1)
            return result[start][end][mask];
        if (mask == 0) {
            if (start != end)
                return result[start][end][mask] = 0;
            return result[start][end][mask] = 1;
        }
        result[start][end][mask] = 0;
        for (int i = 0; i < count; i++) {
            if ((mask >> i & 1) != 0 && (mask & previousSame[i]) == 0) {
                if (from[i] == end)
                    result[start][end][mask] += go(start, to[i], mask - (1 << i));
                else if (to[i] == end)
                    result[start][end][mask] += go(start, from[i], mask - (1 << i));
            }
        }
        return result[start][end][mask];
    }
}
