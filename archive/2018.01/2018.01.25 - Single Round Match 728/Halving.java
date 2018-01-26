package net.egork;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Integer.MAX_VALUE;

public class Halving {
    public int minSteps(int[] a) {
        int n = a.length;
        Map<Integer, Integer>[] variants = new Map[n];
        for (int i = 0; i < n; i++) {
            variants[i] = new HashMap<>();
            NavigableSet<Integer> candidates = new TreeSet<>();
            candidates.add(a[i]);
            variants[i].put(a[i], 0);
            while (!candidates.isEmpty()) {
                int current = candidates.pollLast();
                if (current == 1) {
                    continue;
                }
                int cost = variants[i].get(current) + 1;
                int next = current / 2;
                candidates.add(next);
                if (!variants[i].containsKey(next) || variants[i].get(next) > cost) {
                    variants[i].put(next, cost);
                }
                next = (current + 1) / 2;
                candidates.add(next);
                if (!variants[i].containsKey(next) || variants[i].get(next) > cost) {
                    variants[i].put(next, cost);
                }
            }
        }
        int answer = MAX_VALUE;
        for (int key : variants[0].keySet()) {
            int candidate = 0;
            boolean good = true;
            for (int i = 0; i < n && good; i++) {
                if (!variants[i].containsKey(key)) {
                    good = false;
                } else {
                    candidate += variants[i].get(key);
                }
            }
            if (good) {
                answer = Math.min(answer, candidate);
            }
        }
        return answer;
    }
}
