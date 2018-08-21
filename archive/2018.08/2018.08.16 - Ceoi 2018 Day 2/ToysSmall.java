package net.egork;

import net.egork.generated.collections.list.LongList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sort;
import static net.egork.numbers.IntegerUtils.getDivisors;

public class ToysSmall {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        LongList divisors = getDivisors(n);
        divisors.sort();
        IntHashSet[] results = new IntHashSet[divisors.size()];
        results[0] = new IntHashSet();
        results[0].add(0);
        for (int i = 1; i < divisors.size(); i++) {
            results[i] = new IntHashSet();
            for (int j = 0; j < i; j++) {
                if (divisors.get(i) % divisors.get(j) == 0) {
                    int delta = (int) (divisors.get(i) / divisors.get(j) - 1);
                    for (int k : results[j]) {
                        results[i].add(k + delta);
                    }
                }
            }
        }
        int[] answer = results[results.length - 1].toArray();
        sort(answer);
        out.printLine(answer.length);
        out.printLine(answer);
    }
}
