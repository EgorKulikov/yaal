package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		final long yearDelta = in.readInt();
		final int[] year = new int[count];
		int[] health = new int[count];
		IOUtils.readIntArrays(in, year, health);
		final long[] sums = ArrayUtils.partialSums(health);
		LongIntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.min(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return Math.min(was, delta);
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return Math.min(value, delta);
			}

			@Override
			protected long neutralValue() {
				return Long.MAX_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return Long.MAX_VALUE;
			}

			@Override
			protected long initValue(int index) {
				return sums[index] - yearDelta * year[index];
			}
		};
		for (int i = 0; i < queryCount; i++) {
			int born = in.readInt();
			int test = in.readInt();
			int start = in.readInt();
			int firstVaccination = Arrays.binarySearch(year, born);
			if (firstVaccination < 0)
				firstVaccination = -firstVaccination - 1;
			int lastVaccination = Arrays.binarySearch(year, test);
			if (lastVaccination < 0)
				lastVaccination = -lastVaccination - 2;
			if (firstVaccination > lastVaccination) {
				long raw = start - yearDelta * (test - born);
				if (raw < 0)
					raw = 0;
				out.printLine(raw);
				continue;
			}
			long atBorn = -yearDelta * born + sums[firstVaccination];
			long atWorstPoint = tree.query(firstVaccination, lastVaccination) - atBorn + start;
			long raw = start - (test - born) * yearDelta + sums[lastVaccination + 1] - sums[firstVaccination];
			if (atWorstPoint < 0)
				raw -= atWorstPoint;
			if (raw < 0)
				raw = 0;
			out.printLine(raw);
		}
    }
}
