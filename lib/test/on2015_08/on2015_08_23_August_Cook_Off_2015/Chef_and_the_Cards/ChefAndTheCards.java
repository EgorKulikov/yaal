package on2015_08.on2015_08_23_August_Cook_Off_2015.Chef_and_the_Cards;


import net.egork.collections.intcollection.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndTheCards {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] b = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(a, b);
		ArrayUtils.orderBy(b, a);
		int answer = 0;
		boolean[] processed = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (a[i] == i) {
				answer++;
				processed[i] = true;
			}
		}
		int[] cycles = new int[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (processed[i]) {
				continue;
			}
			int current = i;
			int length = 0;
			do {
				processed[current] = true;
				current = a[current];
				length++;
			} while (current != i);
			cycles[count++] = length;
		}
		cycles = Arrays.copyOf(cycles, count);
		int[] per = new int[count];
		final int[] finalCycles = cycles;
		Heap heap = new Heap((x, y) -> {
			int nY = finalCycles[y] / (per[y] + 1);
			int nX = finalCycles[x] / (per[x] + 1);
			int val = nY - nX;
			if (val != 0) {
				return val;
			}
			int cY = per[y] == 0 ? 0 : finalCycles[y] / per[y] == nY ? finalCycles[y] % per[y] : per[y];
			int cX = per[x] == 0 ? 0 : finalCycles[x] / per[x] == nX ? finalCycles[x] % per[x] : per[x];
			return finalCycles[y] % (per[y] + 1) - cY - finalCycles[x] % (per[x] + 1) + cX;
		}, count);
		for (int i = 0; i < count; i++) {
			heap.add(i);
		}
		int min = Integer.MAX_VALUE;
		int additional = 0;
		for (int delta = 1; delta < n; delta++) {
			if (answer >= n - delta) {
				break;
			}
			int at = heap.poll();
			per[at]++;
			if (cycles[at] / per[at] - 1 < min) {
				min = cycles[at] / per[at] - 1;
				additional = delta;
				additional -= per[at];
			} else if (per[at] == 1 || cycles[at] / per[at] != cycles[at] / (per[at] - 1)) {
				additional -= per[at] - 1;
			} else {
				additional -= cycles[at] % (per[at] - 1);
			}
			additional += cycles[at] % per[at];
			heap.add(at);
			answer = Math.max(answer, min * delta + additional);
		}
		out.printLine(answer);
	}
}
