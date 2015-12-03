package on2015_11.on2015_11_22_Grand_Prix_of_Europe___2015.G___Greenhouse_Growth;



import net.egork.collections.intcollection.Heap;
import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	int[] h;
	private boolean[] growA;
	private boolean[] growB;
	private int[] end;
	private int[] start;
	private int[] delay;
	private int[] delayA;
	private int[] delayB;
	private Heap aHeap;
	private Heap bHeap;
	private Heap both;
	int addA;
	int addB;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt() + 2;
		int m = in.readInt();
		int[] hh = IOUtils.readIntArray(in, n - 2);
		h = new int[n];
		System.arraycopy(hh, 0, h, 1, n - 2);
		char[] schedule = IOUtils.readCharArray(in, m);
		growA = new boolean[n];
		growB = new boolean[n];
		end = ArrayUtils.createOrder(n);
		start = ArrayUtils.createOrder(n);
		delay = new int[n];
		delayA = new int[n];
		delayB = new int[n];
		aHeap = new Heap((f, s) -> delayA[f] - delayA[s], n);
		bHeap = new Heap((f, s) -> delayB[f] - delayB[s], n);
		both = new Heap((f, s) -> delay[f] - delay[s], n);
		IntSet set = new IntHashSet();
		for (int i = 1; i < n - 1; i++) {
			resolve(i, set);
		}
		for (int i = 1; i < n - 1; i++) {
			if (i == start[i]) {
				fixGrow(i);
			}
		}
		for (int i = 1; i < n - 1; i++) {
			if (i == start[i]) {
				addBack(i);
			}
		}
		for (char c : schedule) {
			if (c == 'A') {
				addA++;
			} else {
				addB++;
			}
			IntSet toResolve = new IntHashSet();
			while (!aHeap.isEmpty() && delayA[aHeap.peek()] <= addA) {
				toResolve.add(aHeap.poll());
			}
			while (!bHeap.isEmpty() && delayB[bHeap.peek()] <= addB) {
				toResolve.add(bHeap.poll());
			}
			while (!both.isEmpty() && delay[both.peek()] <= addA + addB) {
				toResolve.add(both.poll());
			}
			IntSet toFix = new IntHashSet();
			for (int i : toResolve) {
				resolve(i, toFix);
			}
			for (int i : toFix) {
				fixGrow(i);
			}
			for (int i : toFix) {
				addBack(i);
			}
		}
		int until = -1;
		for (int i = 0; i < n; i++) {
			if (i > until) {
				if (growA[i]) {
					h[i] += addA;
				}
				if (growB[i]) {
					h[i] += addB;
				}
				until = end[i];
			} else {
				h[i] = h[i - 1];
			}
		}
		out.printLine(new IntArray(h).subList(1, n - 1).compute());
	}

	private void resolve(int at, IntSet toFix) {
		if (start[at] != at) {
			return;
		}
		int value = value(at);
		int left = value(start[at - 1]);
		int right = value(end[at] + 1);
		while (value == left || value == right) {
			if (value == left) {
				start[end[at]] = start[at - 1];
				end[start[at - 1]] = end[at];
				start[at] = start[at - 1];
				at = start[at - 1];
			}
			if (value == right) {
				start[end[end[at] + 1]] = at;
				start[end[at] + 1] = at;
				end[at] = end[end[at] + 1];
			}
			left = value(start[at - 1]);
			right = value(end[at] + 1);
		}
		growA[at] = false;
		growB[at] = false;
		h[at] = value;

		toFix.add(start[at - 1]);
		toFix.add(at);
		toFix.add(end[at] + 1);

//		fixGrow(start[at - 1]);
//		fixGrow(at);
//		fixGrow(end[at] + 1);
//
//		addBack(start[at - 1]);
//		addBack(at);
//		addBack(end[at] + 1);
	}

	void fixGrow(int at) {
		if (at == 0 || at == end.length - 1) {
			return;
		}
		both.remove(at);
		aHeap.remove(at);
		bHeap.remove(at);

		int value = value(at);
		int left = value(start[at - 1]);
		int right = value(end[at] + 1);
		growA[at] = left > value;
		growB[at] = right > value;
		h[at] = value;
		if (growA[at]) {
			h[at] -= addA;
		}
		if (growB[at]) {
			h[at] -= addB;
		}
	}

	private void addBack(int at) {
		if (at == 0 || at == end.length - 1) {
			return;
		}
		int value = value(at);
		int lPos = start[at - 1];
		int left = value(start[at - 1]);
		int rPos = end[at] + 1;
		int right = value(end[at] + 1);
		if (growA[at]) {
			if (growB[at]) {
				if (!growA[lPos]) {
					if (!growB[rPos]) {
						delay[at] = addA + addB + Math.min(left - value, right - value);
						both.add(at);
					} else {
						delay[at] = addA + addB + left - value;
						both.add(at);
						delayA[at] = addA + right - value;
						aHeap.add(at);
					}
				} else {
					if (!growB[rPos]) {
						delay[at] = addA + addB + right - value;
						both.add(at);
						delayB[at] = addB + left - value;
						bHeap.add(at);
					} else {
						delayA[at] = addA + right - value;
						aHeap.add(at);
						delayB[at] = addB + left - value;
						bHeap.add(at);
					}
				}
			} else {
				if (!growA[lPos]) {
					delayA[at] = addA + left - value;
					aHeap.add(at);
				}
			}
		} else {
			if (growB[at]) {
				if (!growB[rPos]) {
					delayB[at] = addB + right - value;
					bHeap.add(at);
				}
			}
		}
	}

	private int value(int at) {
		if (at == 0 || at == end.length - 1) {
			return 0;
		}
		int result = h[at];
		if (growA[at]) {
			result += addA;
		}
		if (growB[at]) {
			result += addB;
		}
		return result;
	}
}
