package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AlmostEqual {
	static final int REBUILD = 500;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int delta = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int queryCount = in.readInt();
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		IOUtils.readIntArrays(in, from, to);
		System.err.println("Input");
		AVLTree set = new AVLTree(heights);
		System.err.println("Tree building");
		int lastRebuild = 0;
		long[] sum = new long[count + 1];
		int[] queryOrder = ArrayUtils.order(to);
		int currentQuery = 0;
		long[] total = new long[count + 1];
		long[] answer = new long[queryCount];
		System.err.println("Other preparation");
		long totalQueries = 0;
		long totalProcess = 0;
		for (int i = 0; i < count && currentQuery < queryCount; i++) {
			total[i + 1] = total[i] + set.slice(i, heights[i] - delta, heights[i] + delta);
//			totalQueries -= System.currentTimeMillis();
			while (currentQuery < queryCount && to[queryOrder[currentQuery]] == i) {
				int queryIndex = queryOrder[currentQuery++];
				long result;
				if (from[queryIndex] > lastRebuild)
					result = total[i + 1] - total[from[queryIndex]];
				else
					result = total[i + 1] - (sum[0] - sum[from[queryIndex]]);
				for (int j = Math.max(from[queryIndex], lastRebuild); j <= i; j++)
					result -= set.slice(from[queryIndex], heights[j] - delta, heights[j] + delta);
				answer[queryIndex] = result;
			}
//			totalQueries += System.currentTimeMillis();
			if ((i + 1) % REBUILD == 0) {
//				totalProcess -= System.currentTimeMillis();
				for (int j = i; j >= 0; j--) {
					sum[j] = sum[j + 1] + set.slice(i + 1, heights[j] - delta, heights[j] + delta) -
						set.slice(j + 1, heights[j] - delta, heights[j] + delta);//backward.subSet(start, end).size();
				}
//				totalProcess += System.currentTimeMillis();
				lastRebuild = i + 1;
//				System.err.println("Queries = " + totalQueries + ", process = " + totalProcess + ", at = " + i);
			}
		}
		for (long l : answer)
			out.printLine(l);
    }

	static class AVLTree {
		final int[] heights;
		int[] left = new int[10];
		int[] right = new int[10];
		int[] size = new int[10];
		int[] depth = new int[10];
		int[] value = new int[10];
		int[] root;

		int count;
		int current;

		AVLTree(int[] heights) {
			this.heights = heights;
			root = new int[heights.length + 1];
			root[0] = -1;
			for (int i = 0; i < heights.length; i++)
				add(i);
		}

		private void add(int index) {
			int oldRoot = root[current];
			root[++current] = add(oldRoot, index);
		}

		private int add(int at, int index) {
			if (at == -1) {
				return newVertex(-1, -1, 1, index, 1);
			}
			at = clone(at);
			if (heights[index] < heights[value[at]]) {
				int nLeft = add(left[at], index);
				left[at] = nLeft;
			} else {
				int nRight = add(right[at], index);
				right[at] = nRight;
			}
			return balance(at);
		}

		private int balance(int at) {
			int lDepth = depth(left[at]);
			int rDepth = depth(right[at]);
			if (lDepth - rDepth == 2) {
				int bt = left[at];
				if (depth(left[bt]) >= depth(right[bt])) {
					left[at] = right[bt];
					right[bt] = at;
					updateSizeAndDepth(at);
					updateSizeAndDepth(bt);
					return bt;
				}
				int ct = right[bt];
				right[bt] = left[ct];
				left[at] = right[ct];
				left[ct] = bt;
				right[ct] = at;
				updateSizeAndDepth(at);
				updateSizeAndDepth(bt);
				updateSizeAndDepth(ct);
				return ct;
			}
			if (rDepth - lDepth == 2) {
				int bt = right[at];
				if (depth(right[bt]) >= depth(left[bt])) {
					right[at] = left[bt];
					left[bt] = at;
					updateSizeAndDepth(at);
					updateSizeAndDepth(bt);
					return bt;
				}
				int ct = left[bt];
				left[bt] = right[ct];
				right[at] = left[ct];
				right[ct] = bt;
				left[ct] = at;
				updateSizeAndDepth(at);
				updateSizeAndDepth(bt);
				updateSizeAndDepth(ct);
				return ct;
			}
			updateSizeAndDepth(at);
			return at;
		}

		private void updateSizeAndDepth(int at) {
			size[at] = size(left[at]) + size(right[at]) + 1;
			depth[at] = Math.max(depth(left[at]), depth(right[at])) + 1;
		}

		private int depth(int at) {
			if (at == -1)
				return 0;
			return depth[at];
		}

		private int size(int at) {
			if (at == -1)
				return 0;
			return size[at];
		}

		private int newVertex(int cLeft, int cRight, int cDepth, int cValue, int cSize) {
			ensureCapacity(count + 1);
			left[count] = cLeft;
			right[count] = cRight;
			depth[count] = cDepth;
			value[count] = cValue;
			size[count] = cSize;
			return count++;
		}

		private int clone(int at) {
			return newVertex(left[at], right[at], depth[at], value[at], size[at]);
		}

		private void ensureCapacity(int length) {
			if (left.length < length) {
				left = Arrays.copyOf(left, 2 * length);
				right = Arrays.copyOf(right, 2 * length);
				size = Arrays.copyOf(size, 2 * length);
				depth = Arrays.copyOf(depth, 2 * length);
				value = Arrays.copyOf(value, 2 * length);
			}
		}

		private int slice(int index, int from, int to) {
			return get(root[index], to) - get(root[index], from - 1);
		}

		private int get(int at, int up) {
			if (at == -1)
				return 0;
			if (heights[value[at]] <= up)
				return size(left[at]) + 1 + get(right[at], up);
			return get(left[at], up);
		}
	}
}
