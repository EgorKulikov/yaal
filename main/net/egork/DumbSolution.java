package net.egork;

import net.egork.collections.heap.Heap;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

/**
 * @author egor@egork.net
 */
public class DumbSolution {
	int[] a;
	int[] b;

	public void init(int n, int[] a, int[] b) {
		ArrayUtils.orderBy(a, b);
		this.a = a.clone();
		this.b = b.clone();
	}

	public String can(int m, int[] k) {
		Heap heap = new Heap((f, s) -> b[f] - b[s], a.length);
		int at = 0;
		Arrays.sort(k);
		for (int i : k) {
			while (at < a.length && a[at] <= i) {
				heap.add(at++);
			}
			while (!heap.isEmpty() && b[heap.peek()] < i) {
				heap.poll();
			}
			if (heap.getSize() < i) {
				return "NO";
			}
			for (int j = 0; j < i; j++) {
				heap.poll();
			}
		}
		return "YES";
	}
}
