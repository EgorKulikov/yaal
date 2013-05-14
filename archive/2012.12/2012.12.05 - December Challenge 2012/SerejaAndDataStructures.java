package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SerejaAndDataStructures {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] numbers = IOUtils.readIntArray(in, count);
        long result = go(numbers, 0, count - 1);
        out.printLine(result);
    }

    private long go(int[] numbers, int from, int to) {
        if (from > to)
            return 1;
        int minimumAt = ArrayUtils.minPosition(numbers, from, to + 1, IntComparator.DEFAULT);
        long result = go(numbers, from, minimumAt - 1) * go(numbers, minimumAt + 1, to) % MOD;
        int i = minimumAt - 1;
        int j = minimumAt + 1;
        while (i >= from || j <= to) {
            int max;
            long current = 0;
            if (i < from || j <= to && numbers[j] < numbers[i])
                max = numbers[j];
            else
                max = numbers[i];
            while (i >= from && numbers[i] <= max) {
                current += j - minimumAt;
                i--;
            }
            while (j <= to && numbers[j] <= max) {
                current += minimumAt - i;
                j++;
            }
            result = result * IntegerUtils.power(max - numbers[minimumAt], current, MOD) % MOD;
        }
        return result;
    }
}
