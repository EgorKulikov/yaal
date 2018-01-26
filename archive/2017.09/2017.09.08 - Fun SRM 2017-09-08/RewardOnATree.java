package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.maxElement;

public class RewardOnATree {
    public int collect(int[] parent, int[] reward) {
        int answer = reward[0];
        int n = parent.length + 1;
        int[] l = new int[n];
        for (int i = 0; i < n - 1; i++) {
            l[i + 1] = l[parent[i]] + 1;
        }
        IntList[] levels = new IntList[maxElement(l) + 1];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = new IntArrayList();
        }
        for (int i = 0; i < n; i++) {
            levels[l[i]].add(i);
        }
        int[] result = new int[n];
        result[0] = min(reward[0], 0);
        int lastLevel = max(reward[0], 0);
        for (int i = 1; i < levels.length; i++) {
            int curLevel = 0;
            for (int j : levels[i]) {
                result[j] = result[parent[j - 1]] + min(0, reward[j]) + lastLevel;
                for (int k : levels[i - 1]) {
                    result[j] = Math.max(result[j], result[k] + min(0, reward[j]) + min(0, reward[parent[j - 1]]) +
                            lastLevel);
                }
                curLevel += max(0, reward[j]);
            }
            for (int j : levels[i]) {
                answer = Math.max(answer, result[j] + curLevel);
            }
            lastLevel = curLevel;
        }
        return answer;
    }
}
