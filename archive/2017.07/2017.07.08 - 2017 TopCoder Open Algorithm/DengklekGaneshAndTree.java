package net.egork;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Math.max;
import static java.util.Collections.sort;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.MiscUtils.MOD7;

public class DengklekGaneshAndTree {
    boolean[] sureGood;
    List<IntIntPair> segments;

    long[][] ways;

    public int getCount(String labels, int[] parents) {
        int n = labels.length();
        int[] level = new int[n];
        for (int i = 0; i < n - 1; i++) {
            level[i + 1] = level[parents[i]] + 1;
        }
        boolean[] processed = new boolean[n];
        sureGood = new boolean[maxElement(level) + 1];
        segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (processed[i]) {
                continue;
            }
            IntSet vertices = new IntHashSet();
            vertices.add(i);
            boolean isUpper = isUpperCase(labels.charAt(i));
            for (int j = i + 1; j < n; j++) {
                if (vertices.contains(parents[j - 1]) && toLowerCase(labels.charAt(j)) == toLowerCase(labels.charAt
                        (parents[j - 1]))) {
                    vertices.add(j);
                    isUpper |= isUpperCase(labels.charAt(j));
                }
            }
            int minLevel = level[i];
            int maxLevel = level[i];
            for (int j : vertices) {
                processed[j] = true;
                maxLevel = max(maxLevel, level[j]);
            }
            if (isUpper) {
                for (int j = minLevel; j <= maxLevel; j++) {
                    sureGood[j] = true;
                }
            } else {
                segments.add(new IntIntPair(minLevel, maxLevel));
            }
        }
        ways = new long[sureGood.length + 1][segments.size() + 1];
        fill(ways, -1);
        sort(segments);
        return (int) go(0, 0);
    }

    private long go(int cLevel, int cSegment) {
        if (ways[cLevel][cSegment] != -1) {
            return ways[cLevel][cSegment];
        }
        if (cSegment == segments.size()) {
            if (cLevel == sureGood.length) {
                return ways[cLevel][cSegment] = 1;
            }
            return ways[cLevel][cSegment] = 0;
        }
        if (cLevel == sureGood.length) {
            return ways[cLevel][cSegment] = go(cLevel, cSegment + 1) * 2 % MOD7;
        }
        if (sureGood[cLevel]) {
            return ways[cLevel][cSegment] = go(cLevel + 1, cSegment);
        }
        if (segments.get(cSegment).first > cLevel) {
            return ways[cLevel][cSegment] = 0;
        }
        return ways[cLevel][cSegment] = (go(max(cLevel, segments.get(cSegment).second + 1), cSegment + 1) + go(cLevel,
                cSegment + 1)) % MOD7;
    }
}
