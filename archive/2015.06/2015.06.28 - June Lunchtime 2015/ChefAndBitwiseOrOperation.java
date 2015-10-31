package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.sequence.Array;
import net.egork.geometry.GeometryUtils;
import net.egork.graph.MinCostFlow;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndBitwiseOrOperation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, size);
        int[][] positions = new int[size][32];
        int[][] ors = new int[size][32];
        int[] qty = new int[size];
        for (int i = 0; i < size; i++) {
            int current = 0;
            for (int j = i; j >= 0; j--) {
                if ((current | array[j]) != current) {
                    current |= array[j];
                    ors[i][qty[i]] = current;
                    positions[i][qty[i]++] = j;
                }
            }
            positions[i] = Arrays.copyOf(positions[i], qty[i]);
        }
        long[] answer = new long[size + 1];
        for (int i = 0; i < count; i++) {
            for (int j = size - count + i + 1; j > i; j--) {
                for (int k = 0; k < positions[j - 1].length; k++) {
                    answer[j] = Math.max(answer[j], answer[positions[j - 1][k]] + ors[j - 1][k]);
                }
            }
        }
        process(answer);
        new IntComparator() {
            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            public int compare(int first, int second) {
                return 0;
            }
        };
        new MinCostFlow(null, 0, 0, false) {
            @Override
            public Pair<Long, Long> minCostMaxFlow() {
                return super.minCostMaxFlow();
            }
        };
        out.printLine(answer[size]);
    }

    @Override
    public String toString() {
        return "";
    }

    private static <T>T process(T x) {
        return x;
    }
}
