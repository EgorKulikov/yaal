package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CowJog {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long time = in.readLong();
        long[] position = new long[count];
        long[] speed = new long[count];
        IOUtils.readLongArrays(in, position, speed);
        for (int i = 0; i < count; i++) {
            position[i] += speed[i] * time;
        }
        int[] order = ArrayUtils.createOrder(count);
        ArrayUtils.sort(order, new IntComparator() {
            @Override
            public int compare(int first, int second) {
                if (position[first] != position[second]) {
                    return Long.compare(position[first], position[second]);
                }
                return second - first;
            }
        });
        ArrayUtils.reverse(order);
        int[] seq = new int[count];
        int length = 0;
        for (int i : order) {
            int pos = -Arrays.binarySearch(seq, 0, length, i) - 1;
            seq[pos] = i;
            length = Math.max(length, pos + 1);
        }
        out.printLine(length);
    }
}
