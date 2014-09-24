package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InverseRMQ {
    public String possible(int n, int[] A, int[] B, int[] ans) {
		int count = ans.length;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				if (ans[i] == ans[j] && (A[i] > B[j] || B[j] < A[i])) {
					return "Impossible";
				}
			}
		}
		int[] unique = ans.clone();
		List<IntPair> left = getSegments(n, unique);
		int[] ends = new int[2 * count];
		System.arraycopy(A, 0, ends, 0, count);
		System.arraycopy(B, 0, ends, count, count);
		List<IntPair> right = getSegments(n, ends);
		Graph graph = new Graph(left.size() + right.size() + 2);
		int source = left.size() + right.size();
		int sink = source + 1;
		for (int i = 0; i < left.size(); i++) {
			IntPair curLeft = left.get(i);
			graph.addFlowEdge(source, i, curLeft.second - curLeft.first + 1);
			boolean isSpecial = ArrayUtils.count(ans, curLeft.first) != 0;
			if (isSpecial) {
				int from = 1;
				int to = n;
				for (int j = 0; j < ans.length; j++) {
					if (ans[j] == curLeft.first) {
						from = Math.max(from, A[j]);
						to = Math.min(to, B[j]);
					}
				}
				for (int j = 0; j < right.size(); j++) {
					IntPair curRight = right.get(j);
					if (curRight.first >= from && curRight.second <= to) {
						boolean good = true;
						for (int k = 0; k < count; k++) {
							if (A[k] <= curRight.first && B[k] >= curRight.second && ans[k] < curLeft.first) {
								good = false;
								break;
							}
						}
						if (good) {
							graph.addFlowEdge(i, j + left.size(), n);
						}
					}
				}
			} else {
				for (int j = 0; j < right.size(); j++) {
					IntPair curRight = right.get(j);
					boolean good = true;
					for (int k = 0; k < count; k++) {
						if (A[k] <= curRight.first && B[k] >= curRight.second && ans[k] < curLeft.first) {
							good = false;
							break;
						}
					}
					if (good) {
						graph.addFlowEdge(i, j + left.size(), n);
					}
				}
			}
		}
		for (int i = 0; i < right.size(); i++) {
			IntPair curRight = right.get(i);
			graph.addFlowEdge(i + left.size(), sink, curRight.second - curRight.first + 1);
		}
		return MaxFlow.dinic(graph, source, sink) == n ? "Possible" : "Impossible";
    }

	protected List<IntPair> getSegments(int n, int[] unique) {
		Arrays.sort(unique);
		unique = ArrayUtils.unique(unique);
		List<IntPair> left = new ArrayList<>();
		if (unique[0] > 1) {
			left.add(new IntPair(1, unique[0] - 1));
		}
		left.add(new IntPair(unique[0], unique[0]));
		for (int i = 1; i < unique.length; i++) {
			if (unique[i] - unique[i - 1] > 1) {
				left.add(new IntPair(unique[i - 1] + 1, unique[i] - 1));
			}
			left.add(new IntPair(unique[i], unique[i]));
		}
		if (unique[unique.length - 1] != n) {
			left.add(new IntPair(unique[unique.length - 1] + 1, n));
		}
		return left;
	}
}
