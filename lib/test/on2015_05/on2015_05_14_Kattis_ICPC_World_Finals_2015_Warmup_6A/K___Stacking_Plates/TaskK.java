package on2015_05.on2015_05_14_Kattis_ICPC_World_Finals_2015_Warmup_6A.K___Stacking_Plates;


import net.egork.collections.map.EHashMap;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        int count = in.readInt();
        int[][] stacks = new int[count][];
        for (int i = 0; i < count; i++) {
            int size = in.readInt();
            stacks[i] = IOUtils.readIntArray(in, size);
        }
        NavigableSet<Integer> all = new TreeSet<>();
        Map<Integer, IntList> where = new EHashMap<>();
        for (int j = 0; j < stacks.length; j++) {
            int[] stack = stacks[j];
            for (int i : stack) {
                all.add(i);
                if (!where.containsKey(i)) {
                    where.put(i, new IntArrayList());
                    where.get(i).add(j);
                } else if (where.get(i).last() != j) {
                    where.get(i).add(j);
                }
            }
        }
        Map<IntIntPair, IntList> good = new TreeMap<>();
        int last = -1;
        for (int i : all) {
            if (last != -1) {
                good.put(new IntIntPair(last, i), new IntArrayList());
            }
            last = i;
        }
        int answer = count - 1;
        for (int i = 0; i < stacks.length; i++) {
            int[] stack = stacks[i];
            for (int j = 1; j < stack.length; j++) {
                if (stack[j] != stack[j - 1]) {
                    answer += 2;
                    IntIntPair pair = new IntIntPair(stack[j - 1], stack[j]);
                    if (good.containsKey(pair)) {
                        good.get(pair).add(i);
                    }
                }
            }
        }
        int[] current = new int[count + 1];
        int[] next = new int[count + 1];
        for (Map.Entry<IntIntPair, IntList> entry : good.entrySet()) {
            Arrays.fill(next, 0);
            int max = -1;
            int at = -1;
            int second = -1;
            for (int i = 0; i <= count; i++) {
                if (current[i] > max) {
                    second = max;
                    max = current[i];
                    at = i;
                } else {
                    second = Math.max(second, current[i]);
                }
            }
            Arrays.fill(next, max);
            for (int i : entry.getValue().toArray()) {
                if (i != at || where.get(entry.getKey().first).size() == 1) {
                    next[i] = max + 1;
                } else {
                    next[i] = second + 1;
                }
            }
            int[] temp = current;
            current = next;
            next = temp;
        }
        answer -= ArrayUtils.maxElement(current) * 2;
        out.printLine("Case " + testNumber + ":", answer);
    }
}
