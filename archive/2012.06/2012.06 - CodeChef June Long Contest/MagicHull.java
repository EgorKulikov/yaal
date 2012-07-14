package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.PrintStream;

public class MagicHull {
	static PrintStream err = System.err;
	private static long time;
//	static PrintStream err = new PrintStream(new OutputStream() {
//		@Override
//		public void write(int b) throws IOException {
//		}
//	});

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		time = System.currentTimeMillis();
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		Map left = new Map();
		boolean[] same = new boolean[count];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (x[i] == x[j] && y[i] == y[j])
					same[j] = true;
			}
		}
		err.println("Read = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		int[] cx = new int[4];
		int[] cy = new int[4];
		long result = 0;
		for (int i = 0; i < count; i++) {
			if (same[i])
				continue;
			cx[1] = x[i];
			cy[1] = y[i];
			for (int j = 0; j < count; j++) {
				if (same[j])
					continue;
				cx[2] = cx[1] + x[j];
				cy[2] = cy[1] + y[j];
				long sign = vectorProduct(x[i], y[i], x[j], y[j]);
				if (sign < 0 || sign == 0 && i > j)
					continue;
				for (int k = 0; k < count; k++) {
					if (same[k])
						continue;
					long secondSign = vectorProduct(x[j], y[j], x[k], y[k]);
					if (secondSign < 0 || secondSign == 0 && j > k)
						continue;
					cx[3] = cx[2] + x[k];
					cy[3] = cy[2] + y[k];
					if (vectorProduct(x[k], y[k], -cx[3], -cy[3]) < 0 || vectorProduct(-cx[3], -cy[3], x[i], y[i]) < 0)
						continue;
					long square = square(cx, cy);
					if (cx[3] == 0 && cy[3] == 0) {
						result = Math.max(result, square);
						continue;
					}
					left.put(cx[3], cy[3], square);
				}
			}
		}
		err.println("First stage = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
//		left.rebuild();
		for (int i = 0; i < count; i++) {
			cx[1] = -x[i];
			cy[1] = -y[i];
			result = Math.max(result, left.get(cx[1], cy[1]));
			for (int j = 0; j < count; j++) {
				cx[2] = cx[1] - x[j];
				cy[2] = cy[1] - y[j];
				int sign = Long.signum(vectorProduct(x[i], y[i], x[j], y[j]));
				if (sign < 0)
					continue;
				long triangleSquare = triangleSquare(cx, cy);
				result = Math.max(result, left.get(cx[2], cy[2]) + triangleSquare);
			}
		}
		err.println("Second stage = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		result = Math.max(result, left.hexagon());
		err.println("Third stage = " + (System.currentTimeMillis() - time));
		out.printFormat("%.1f\n", result / 2d);
	}

	private long triangleSquare(int[] x, int[] y) {
		return Math.abs((long)(x[0] + x[1]) * (y[0] - y[1]) + (long)(x[1] + x[2]) * (y[1] - y[2]) + (long)(x[2] + x[0]) * (y[2] - y[0]));
	}

	private long square(int[] x, int[] y) {
		long result = 0;
		for (int i = 0; i < 4; i++)
			result += (long)(x[i] + x[(i + 1) & 3]) * (y[i] - y[(i + 1) & 3]);
		return Math.abs(result);
	}

	private long vectorProduct(long ax, long ay, long bx, long by) {
		return ax * by - bx * ay;
	}

	static class Map {
		private static final int SIZE = 3375007;
		private static final int SHIFT = 1125001;
		long[] key = new long[SIZE];
		long[] value = new long[SIZE];

		Map() {
		}

		void put(long x, long y, long value) {
			long key = x * Integer.MAX_VALUE + y;
			int index = (int) (key % SIZE);
			if (index < 0)
				index += SIZE;
			while (this.key[index] != 0 && this.key[index] != key) {
				index = index + SHIFT;
				if (index >= SIZE)
					index -= SIZE;
			}
			this.key[index] = key;
			this.value[index] = Math.max(this.value[index], value);
		}

		/*
		void rebuild() {
			int[] order = new int[index];
			for (int i = 0; i < index; i++)
				order[i] = i;
			err.println("Rebuild init = " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			sort(order, 0, index - 1);
			err.println("Rebuild sort = " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			long[] newKey = new long[index];
			long[] newValue = new long[index];
			for (int i = 0; i < index; i++) {
				newKey[i] = key[order[i]];
				newValue[i] = value[order[i]];
			}
			err.println("Rebuild reorder = " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			int start = 0;
			long max = 0;
			key = newKey;
			value = newValue;
			for (int i = 0; i < index; i++) {
				max = Math.max(max, value[i]);
				if (i != index - 1 && key[i] == key[i + 1])
					continue;
				for (int j = start; j <= i; j++)
					value[j] = max;
				start = i + 1;
				max = 0;
			}
			err.println("Rebuild maximize = " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
		}

		private void sort(int[] order, int from, int to) {
			if (from >= to)
				return;
			long split = key[order[from]];
			int originalFrom = order[from];
			int index = from;
			int position = from;
			for (int i = from + 1; i <= to; i++) {
				if (key[order[i]] <= split) {
					if (position == index)
						position = i;
					int temp = order[index];
					order[index++] = order[i];
					order[i] = temp;
				}
			}
			sort(order, from, index - 1);
			order[position] = order[index];
			order[index] = originalFrom;
			sort(order, index + 1, to);
		}
		*/
		long get(long x, long y) {
			long key = x * Integer.MAX_VALUE + y;
			return get(key);
		}

		private long get(long key) {
			int index = (int) (key % SIZE);
			if (index < 0)
				index += SIZE;
			while (this.key[index] != key && this.key[index] != 0) {
				index = index + SHIFT;
				if (index >= SIZE)
					index -= SIZE;
			}
			if (this.key[index] == 0)
				return Long.MIN_VALUE;
			return value[index];
		}

		public long hexagon() {
			long result = 0;
			for (int i = 0; i < SIZE; i++) {
				if (key[i] != 0)
					result = Math.max(result, value[i] + get(-key[i]));
			}
			return result;
		}
	}
}
