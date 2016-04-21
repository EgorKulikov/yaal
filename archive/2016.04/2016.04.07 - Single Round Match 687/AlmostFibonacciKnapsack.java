package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class AlmostFibonacciKnapsack {
    public int[] getIndices(long x) {
        LongList numbers = new LongArrayList();
        numbers.add(2);
        numbers.add(3);
        while (numbers.last() < x) {
            numbers.add(numbers.last() + numbers.get(numbers.size() - 2) - 1);
        }
        IntList answer = new IntArrayList();
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) == x) {
                answer.add(i + 1);
                break;
            }
            if (numbers.get(i) + 1 == x) {
                answer.add(i);
                answer.add(i - 1);
                break;
            }
            if (numbers.get(i) < x) {
                x -= numbers.get(i);
                answer.add(i + 1);
            }
        }
        return answer.toArray();
    }
}
