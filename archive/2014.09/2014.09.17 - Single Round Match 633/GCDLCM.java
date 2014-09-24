package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;

import java.util.Arrays;

public class GCDLCM {
    public String possible(int n, String type, int[] A, int[] B, int[] C) {
		IntHashMap divisors = new IntHashMap();
		for (int i : C) {
			for (int j = 2; j * j <= i; j++) {
				if (i % j == 0) {
					int power = 0;
					do {
						i /= j;
						power++;
					} while (i % j == 0);
					if (!divisors.contains(j) || divisors.get(j) < power) {
						divisors.put(j, power);
					}
				}
			}
			if (i != 1 && !divisors.contains(i)) {
				divisors.put(i, 1);
			}
		}
		int[] qty = new int[type.length()];
		for (int d : divisors.toArray()) {
			int max = divisors.get(d) + 1;
			for (int i = 0; i < type.length(); i++) {
				int current = 0;
				int copy = C[i];
				while (copy % d == 0) {
					copy /= d;
					current++;
				}
				qty[i] = current;
			}
			Graph graph = new Graph(2 * max * n);
			for (int i = 0; i < n; i++) {
				for (int j = i * max + 1; j < (i + 1) * max; j++) {
					graph.addSimpleEdge(j, j - 1);
					graph.addSimpleEdge(max * n + j - 1, max * n + j);
				}
			}
			for (int i = 0; i < type.length(); i++) {
				if (type.charAt(i) == 'G') {
					for (int j = 0; j <= qty[i]; j++) {
						graph.addSimpleEdge(max * n + A[i] * max + j, A[i] * max + j);
						graph.addSimpleEdge(max * n + B[i] * max + j, B[i] * max + j);
					}
					for (int j = qty[i] + 1; j < max; j++) {
						graph.addSimpleEdge(A[i] * max + j, max * n + B[i] * max + qty[i] + 1);
						graph.addSimpleEdge(B[i] * max + j, max * n + A[i] * max + qty[i] + 1);
					}
				} else {
					for (int j = qty[i] + 1; j < max; j++) {
						graph.addSimpleEdge(A[i] * max + j, max * n + A[i] * max + j);
						graph.addSimpleEdge(B[i] * max + j, max * n + B[i] * max + j);
					}
					for (int j = 0; j <= qty[i]; j++) {
						graph.addSimpleEdge(max * n + A[i] * max + j, B[i] * max + qty[i]);
						graph.addSimpleEdge(max * n + B[i] * max + j, A[i] * max + qty[i]);
					}
				}
			}
			int[] color = StronglyConnectedComponents.kosaraju(graph).first;
			for (int i = 0; i < max * n; i++) {
				if (color[i] == color[i + max * n]) {
					return "Solution does not exist";
				}
			}
		}
		return "Solution exists";
    }
}
