package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.CPPMap;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.Factory;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int segmentCount = in.readInt();
		int circleCount = in.readInt();
		int[] x0 = new int[segmentCount];
		int[] y0 = new int[segmentCount];
		int[] x1 = new int[segmentCount];
		int[] y1 = new int[segmentCount];
		IOUtils.readIntArrays(in, x0, y0, x1, y1);
		int[] x = new int[circleCount];
		int[] y = new int[circleCount];
		int[] r = new int[circleCount];
		IOUtils.readIntArrays(in, x, y, r);
		for (int i = 0; i < segmentCount; i++) {
			x0[i] *= 2;
			y0[i] *= 2;
			x1[i] *= 2;
			y1[i] *= 2;
		}
		for (int i = 0; i < circleCount; i++) {
			x[i] *= 2;
			y[i] *= 2;
			r[i] *= 2;
		}
		Map<Line, IntList> starts = new CPPMap<Line, IntList>(new Factory<IntList>() {
			public IntList create() {
				return new IntArrayList(1);
			}
		});
		Map<Line, IntList> ends = new CPPMap<Line, IntList>(new Factory<IntList>() {
			public IntList create() {
				return new IntArrayList(1);
			}
		});
		for (int i = 0; i < segmentCount; i++) {
			int a = y0[i] - y1[i];
			int b = x1[i] - x0[i];
			int g = IntegerUtils.gcd(a, b);
			a /= g;
			b /= g;
			if (a < 0 || a == 0 && b < 0) {
				a = -a;
				b = -b;
			}
			int c = -a * x0[i] - b * y0[i];
			Line key = new Line(a, b, c);
			int from = b * x0[i] - a * y0[i];
			int to = b * x1[i] - a * y1[i];
			if (from > to) {
				int temp = from;
				from = to;
				to = temp;
			}
			starts.get(key).add(from);
			ends.get(key).add(to);
		}
		Map<Line, long[]> startsMap = new EHashMap<Line, long[]>();
		for (Map.Entry<Line, IntList> entry : starts.entrySet()) {
			long[] array = ArrayUtils.asLong(entry.getValue().inPlaceSort().toArray());
			for (int i = 0; i < array.length; i++)
				array[i] *= 2;
			startsMap.put(entry.getKey(), array);
		}
		Map<Line, long[]> endsMap = new EHashMap<Line, long[]>();
		for (Map.Entry<Line, IntList> entry : ends.entrySet()) {
			long[] array = ArrayUtils.asLong(entry.getValue().inPlaceSort().toArray());
			for (int i = 0; i < array.length; i++)
				array[i] *= 2;
			endsMap.put(entry.getKey(), array);
		}
		long answer = 0;
		for (int i = 0; i < circleCount; i++) {
			for (int j = i + 1; j < circleCount; j++) {
				if (r[i] != r[j])
					continue;
				int dx = x[i] - x[j];
				int dy = y[i] - y[j];
				if (dx * dx + dy * dy <= 4 * r[i] * r[i])
					continue;
				int xx = (x[i] + x[j]) / 2;
				int yy = (y[i] + y[j]) / 2;
				int a = x[i] - x[j];
				int b = y[i] - y[j];
				int g = IntegerUtils.gcd(a, b);
				a /= g;
				b /= g;
				if (a < 0 || a == 0 && b < 0) {
					a = -a;
					b = -b;
				}
				Line key = new Line(a, b, -a * xx - b * yy);
				if (startsMap.containsKey(key)) {
					long at = b * xx - a * yy;
					answer -= Arrays.binarySearch(startsMap.get(key), 2 * at + 1) - Arrays.binarySearch(endsMap.get(key), 2 * at - 1);
				}
			}
		}
		out.printLine(answer);
    }

	static class Line {
		final int a, b, c;

		Line(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public boolean equals(Object o) {
			Line line = (Line) o;

			return a == line.a && b == line.b && c == line.c;

		}

		@Override
		public int hashCode() {
			int result = a;
			result = 31 * result + b;
			result = 31 * result + c;
			return result;
		}
	}
}
