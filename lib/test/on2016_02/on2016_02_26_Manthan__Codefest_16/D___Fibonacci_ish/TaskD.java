package on2016_02.on2016_02_26_Manthan__Codefest_16.D___Fibonacci_ish;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.set.EHashSet;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        Set<IntIntPair> processed = new EHashSet<>();
        IntHashMap qty = new IntHashMap();
        for (int i : a) {
            int count = qty.contains(i) ? qty.get(i) : 0;
            qty.put(i, count + 1);
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                IntIntPair key = new IntIntPair(a[i], a[j]);
                if (processed.contains(key)) {
                    continue;
                }
                processed.add(key);
                int last = a[i];
                int current = a[j];
                boolean cycle = false;
                IntList suffix = new IntArrayList();
                suffix.add(last);
                suffix.add(current);
                while (true) {
                    int next = last + current;
                    if (!qty.contains(next)) {
                        break;
                    }
                    key = new IntIntPair(current, next);
                    if (processed.contains(key)) {
                        cycle = true;
                        break;
                    }
                    processed.add(key);
                    suffix.add(next);
                    last = current;
                    current = next;
                }
                if (cycle) {
                    IntHashMap local = new IntHashMap();
                    for (int k = 0; k < suffix.size(); k++) {
                        int val = suffix.get(k);
                        int count = local.contains(val) ? local.get(val) : 0;
                        count++;
                        local.put(val, count);
                    }
                    int full = Integer.MAX_VALUE;
                    for (int k = 0; k < suffix.size(); k++) {
                        int val = suffix.get(k);
                        full = Math.min(full, qty.get(val) / local.get(val));
                    }
                    IntList all = new IntArrayList(suffix);
                    all.addAll(suffix);
                    IntHashMap nLocal = new IntHashMap();
                    for (int k = 0; k < suffix.size(); k++) {
                        int val = suffix.get(k);
                        nLocal.put(val, full * local.get(val));
                    }
                    answer = updateAnswer(qty, nLocal, answer, all, full * suffix.size());
                } else {
                    IntList prefix = new IntArrayList();
                    last = a[j];
                    current = a[i];
                    while (true) {
                        int next = last - current;
                        if (!qty.contains(next)) {
                            break;
                        }
                        processed.add(new IntIntPair(next, current));
                        prefix.add(next);
                        last = current;
                        current = next;
                    }
                    prefix.inPlaceReverse();
                    IntList all = (IntList) prefix.addAll(suffix);
                    answer = updateAnswer(qty, new IntHashMap(), answer, all, 0);
                }
            }
        }
        out.printLine(answer);
    }

    protected int updateAnswer(IntHashMap qty, IntHashMap local, int answer, IntList all, int additional) {
        int start = 0;
        for (int k = 0; k < all.size(); k++) {
            int val = all.get(k);
            int count = local.contains(val) ? local.get(val) : 0;
            count++;
            local.put(val, count);
            while (local.get(val) > qty.get(val)) {
                int prev = all.get(start++);
                local.put(prev, local.get(prev) - 1);
            }
            answer = Math.max(answer, k - start + 1 + additional);
        }
        return answer;
    }
}
