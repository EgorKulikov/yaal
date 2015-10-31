package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.collections.map.EHashMap;

import java.util.Arrays;
import java.util.Map;

public class ModModMod {
    int[] m;
    long[] answer;

    public long findSum(int[] m, int R) {
        answer = new long[m.length + 1];
        Arrays.fill(answer, -1);
        this.m = m;
        for (int i = m.length - 1; i >= 0; i--) {
            get(i);
        }
		return go(0, R);
    }

    private long go(int at, int r) {
        if (at == m.length) {
            return (long)r * (r + 1) / 2;
        }
        return r / m[at] * get(at) + go(at + 1, r % m[at]);
    }

    private long get(int at) {
        if (answer[at] != -1) {
            return answer[at];
        }
        return answer[at] = go(at + 1, m[at] - 1);
    }
}
