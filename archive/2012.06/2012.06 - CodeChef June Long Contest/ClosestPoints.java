package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ClosestPoints {
	private int[] x;
	private int count;
	private int[] y;
	private int[] z;
	private Integer[] xOrder;
	private Integer[] yOrder;
	private Integer[] zOrder;
	Comparator<Integer> first;
	Comparator<Integer> second;
	Comparator<Integer> third;

	private static final int FULL_SCAN = 1;
	private static final int QUERY = 50000;

	int answer;
	long value;
	private double[] rx;
	private double[] ry;
	private double[] rz;
	Random random = new Random(239);
	double angle1 = random.nextDouble();
	double angle2 = random.nextDouble();
	double angle3 = random.nextDouble();
	double cos1 = Math.cos(angle1);
	double cos2 = Math.cos(angle2);
	double cos3 = Math.cos(angle3);
	double sin1 = Math.sin(angle1);
	double sin2 = Math.sin(angle2);
	double sin3 = Math.sin(angle3);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long time = System.currentTimeMillis();
		count = in.readInt();
		x = new int[count];
		y = new int[count];
		z = new int[count];
		IOUtils.readIntArrays(in, x, y, z);
		x = Arrays.copyOf(x, count + 1);
		y = Arrays.copyOf(y, count + 1);
		z = Arrays.copyOf(z, count + 1);
//		rx = new double[count + 1];
//		ry = new double[count + 1];
//		rz = new double[count + 1];
//		for (int i = 0; i < count; i++) {
//			rotate(x[i], y[i], z[i], i);
//		}
//		first = new MyComparator(rx);
//		second = new MyComparator(ry);
//		third = new MyComparator(rz);
		first = new MyOtherComparator(x, y, z);
		second = new MyOtherComparator(y, z, x);
		third = new MyOtherComparator(z, x, y);
		Integer[] order = ArrayUtils.generateOrder(count);
		Node root = createTree(order, 0, count - 1, second, third, first);
		System.err.println("Init " + (System.currentTimeMillis() - time));
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int cx = in.readInt();
			int cy = in.readInt();
			int cz = in.readInt();
			answer = -1;
			value = Long.MAX_VALUE;
			if (i < FULL_SCAN) {
				for (int j = 0; j < count; j++)
					tryAnswer(cx, cy, cz, j);
			} else if (i < QUERY) {
				x[count] = cx;
				y[count] = cy;
				z[count] = cz;
//				rotate(cx, cy, cz, count);
				root.query(1);
			} else
				tryAnswer(cx, cy, cz, 0);
			out.printLine(answer);
		}
	}

	private void rotate(double x, double y, double z, int index) {
		rx[index] = x;
		ry[index] = y;
		rz[index] = z;
		rotate(rx, ry, index, cos1, sin1);
		rotate(rx, rz, index, cos2, sin2);
		rotate(ry, rz, index, cos3, sin3);
	}

	private void rotate(double[] rx, double[] ry, int index, double cos, double sin) {
		double newX = rx[index] * cos - ry[index] * sin;
		double newY = rx[index] * sin + ry[index] * cos;
		rx[index] = newX;
		ry[index] = newY;
	}

	private Node createTree(Integer[] order, int from, int to, Comparator<Integer> first, Comparator<Integer> second, Comparator<Integer> third) {
		if (from > to)
			return null;
		if (from == to)
			return new Node(order[from]);
		Arrays.sort(order, from, to + 1, first);
		int middle = (from + to) >> 1;
		Node left = createTree(order, from, middle - 1, second, third, first);
		Node right = createTree(order, middle + 1, to, second, third, first);
		return new Node(order[middle], first, left, right);
	}

	private void tryAnswer(long cx, long cy, long cz, int candidate) {
		long candidateValue = value(cx, cy, cz, candidate);
		if (candidateValue < value) {
			value = candidateValue;
			answer = candidate;
		}
	}

	private long value(long cx, long cy, long cz, int i) {
		return (x[i] - cx) * (x[i] - cx) + (y[i] - cy) * (y[i] - cy) + (z[i] - cz) * (z[i] - cz) - Long.MAX_VALUE;
	}

	class Node {
		final int vertex;
		final Comparator<Integer> comparator;
		final boolean isLeaf;
		final Node left;
		final Node right;

		Node(int vertex) {
			this(vertex, null, null, null);
		}

		Node(int vertex, Comparator<Integer> comparator, Node left, Node right) {
			this.vertex = vertex;
			this.comparator = comparator;
			this.left = left;
			this.right = right;
			this.isLeaf = comparator == null;
		}

		public void query(int depth) {
			tryAnswer(x[count], y[count], z[count], vertex);
			if (isLeaf)
				return;
			int result = comparator.compare(count, vertex);
			if (result < 0) {
				if (left != null)
					left.query(depth);
				if (right != null && depth != 0)
					right.query(depth - 1);
			} else {
				if (right != null)
					right.query(depth);
				if (left != null && depth != 0)
					left.query(depth - 1);
			}
		}
	}

	static class MyComparator implements Comparator<Integer> {
		final double[] array;

		MyComparator(double[] array) {
			this.array = array;
		}

		public int compare(Integer o1, Integer o2) {
			return Double.compare(array[o1], array[o2]);
		}
	}
	static class MyOtherComparator implements Comparator<Integer> {
		final int[] first, second, third;

		MyOtherComparator(int[] first, int[] second, int[] third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public int compare(Integer o1, Integer o2) {
			if (first[o1] != first[o2])
				return first[o1] - first[o2];
			if (second[o1] != second[o2])
				return second[o1] - second[o2];
			return third[o1] - third[o2];
		}
	}
}
