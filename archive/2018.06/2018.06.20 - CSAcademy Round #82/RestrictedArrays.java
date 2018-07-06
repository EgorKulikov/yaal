package net.egork;

import net.egork.collections.map.Counter;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;

public class RestrictedArrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        IntList variants = new IntArrayList();
        Counter<Integer> counter = new Counter<>();
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                counter.add(a[i - k], -1);
                if (counter.get(a[i - k]) == 0) {
                    counter.remove(a[i - k]);
                }
            }
            if (counter.containsKey(a[i])) {
                variants.add(-1);
            } else {
                variants.add(counter.size());
            }
            counter.add(a[i]);
            maxSize = Math.max(maxSize, counter.size());
        }
        long answer = 1;
        for (int i : variants) {
            if (i != -1) {
                answer *= maxSize - i;
                answer %= MOD7;
            }
        }
        out.printLine(answer);
    }
}
