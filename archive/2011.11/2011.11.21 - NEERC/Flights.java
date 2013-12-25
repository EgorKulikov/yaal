package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Flights {
	static final double EPSILON = 1e-9;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] launchPoint = new int[count];
		int[] centerPoint = new int[count];
		int[] height = new int[count];
		for (int i = 0; i < count; i++) {
			launchPoint[i] = in.readInt();
			centerPoint[i] = in.readInt();
			height[i] = in.readInt();
		}
		IntervalTree tree = new IntervalTree(launchPoint, centerPoint, height);
		int flightCount = in.readInt();
		for (int i = 0; i < flightCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			int start = in.readInt();
			int end = in.readInt();
			out.printLine(tree.getValue(from, to, start, end));
		}
	}
}

class IntervalTree {
	private int[] left;
	private int[] right;
	private SkyLine[] value;

	IntervalTree(int[] launchPoint, int[] centerPoint, int[] height) {
		int arraySize = Integer.highestOneBit(launchPoint.length) << 2;
		left = new int[arraySize];
		right = new int[arraySize];
		value = new SkyLine[arraySize];
		initTree(0, 0, launchPoint.length, launchPoint, centerPoint, height);
	}

	private SkyLine initTree(int root, int from, int to, int[] launchPoint, int[] centerPoint, int[] height) {
		left[root] = from;
		right[root] = to;
		if (to - from == 1)
			return value[root] = SkyLine.singleLaunch(launchPoint[from], centerPoint[from], height[from]);
		else {
			return value[root] = SkyLine.unite(initTree(2 * root + 1, from, (from + to) / 2, launchPoint, centerPoint,
				height), initTree(2 * root + 2, (from + to) / 2, to, launchPoint, centerPoint, height));
		}
	}

	double getValue(int from, int to, int start, int finish) {
		return getValue(0, from, to, start, finish);
	}

	private double getValue(int root, int from, int to, int start, int finish) {
		if (left[root] >= to || right[root] <= from)
			return 0;
		if (from <= left[root] && to >= right[root])
			return value[root].getValue(start, finish);
		return Math.max(getValue(2 * root + 1, from, to, start, finish),
			getValue(2 * root + 2, from, to, start, finish));
	}
}

class SkyLine {
	final double[] parts;
	final Parabola[] parabolas;
	int length;
	private int[] left;
	private int[] right;
	private double[] value;

	SkyLine(double[] parts, Parabola[] parabolas) {
		this.parts = parts;
		this.parabolas = parabolas;
		this.length = parabolas.length;
		initialize();
	}

	void initialize() {
		int arraySize = Integer.highestOneBit(length) << 2;
		left = new int[arraySize];
		right = new int[arraySize];
		value = new double[arraySize];
		initTree(0, 0, length);
	}

	private double initTree(int root, int from, int to) {
		left[root] = from;
		right[root] = to;
		if (to - from == 1)
			return value[root] = Math.max(parabolas[from].value(parts[from]), parabolas[from].value(parts[to]));
		else {
			return value[root] = Math.max(initTree(2 * root + 1, from, (from + to) / 2),
				initTree(2 * root + 2, (from + to) / 2, to));
		}
	}

	double getValue(double from, double to) {
		int left = Arrays.binarySearch(parts, from);
		if (left < 0)
			left = -left - 2;
		int right = Arrays.binarySearch(parts, to);
		if (right < 0)
			right = -right - 2;
		right = Math.min(length - 1, right);
		if (left == right)
			return Math.max(parabolas[left].value(from), parabolas[left].value(to));
		double result = 0;
		if (left != right - 1)
			result = Math.max(result, getValue(0, left + 1, right));
		result = Math.max(result, Math.max(parabolas[left].value(from), parabolas[left].value(parts[left + 1])));
		result = Math.max(result, Math.max(parabolas[right].value(to), parabolas[right].value(parts[right])));
		return result;
	}

	private double getValue(int root, int from, int to) {
		if (left[root] >= to || right[root] <= from)
			return 0;
		if (from <= left[root] && to >= right[root])
			return value[root];
		return Math.max(getValue(2 * root + 1, from, to), getValue(2 * root + 2, from, to));
	}

	static SkyLine singleLaunch(double launchPoint, double centerPoint, int height) {
		double fallPoint = 2 * centerPoint - launchPoint;
		fallPoint = Math.min(fallPoint, 50000);
		double[] parts = new double[5];
		Parabola[] parabolas = new Parabola[4];
		int index = 0;
		if (launchPoint != 0) {
			parts[index + 1] = launchPoint;
			parabolas[index++] = Parabola.ZERO;
		}
		Parabola parabola = new Parabola(centerPoint, launchPoint, height);
		parts[index + 1] = centerPoint;
		parabolas[index++] = parabola;
		if (centerPoint != 50000) {
			parts[index + 1] = fallPoint;
			parabolas[index++] = parabola;
		}
		if (fallPoint != 50000) {
			parts[index + 1] = 50000;
			parabolas[index++] = Parabola.ZERO;
		}
		return new SkyLine(Arrays.copyOf(parts, index + 1), Arrays.copyOf(parabolas, index));
	}

	static SkyLine unite(SkyLine left, SkyLine right) {
		double[] parts = new double[(left.length + right.length - 1) * 3 + 1];
		Parabola[] parabolas = new Parabola[parts.length - 1];
		int index = 0;
		int leftIndex = 0;
		int rightIndex = 0;
		double from = 0;
		while (from != 50000) {
			double to;
			Parabola leftParabola = left.parabolas[leftIndex];
			Parabola rightParabola = right.parabolas[rightIndex];
			if (left.parts[leftIndex + 1] < right.parts[rightIndex + 1])
				to = left.parts[++leftIndex];
			else
				to = right.parts[++rightIndex];
			if (to == from)
				continue;
			if (leftParabola == Parabola.ZERO) {
				parts[index + 1] = to;
				parabolas[index++] = rightParabola;
				from = to;
				continue;
			}
			if (rightParabola == Parabola.ZERO) {
				parts[index + 1] = to;
				parabolas[index++] = leftParabola;
				from = to;
				continue;
			}
			int oldIndex = index;
			index = binarySearch(parts, parabolas, oldIndex, from, to, leftParabola, rightParabola);
//			int preciseIndex = index;
//			index = precise(parts, parabolas, oldIndex, from, to, leftParabola, rightParabola);
//			if (index != preciseIndex) {
//				index = binarySearch(parts, parabolas, oldIndex, from, to, leftParabola, rightParabola);
//				index = precise(parts, parabolas, oldIndex, from, to, leftParabola, rightParabola);
//				throw new RuntimeException();
//			}
			from = to;
		}
		return new SkyLine(Arrays.copyOf(parts, index + 1), Arrays.copyOf(parabolas, index));
	}

	private static int binarySearch(double[] parts, Parabola[] parabolas, int index, double from, double to, Parabola leftParabola, Parabola rightParabola) {
		double fromValue = leftParabola.value(from) - rightParabola.value(from);
		double toValue = leftParabola.value(to) - rightParabola.value(to);
		double centerPoint;
		if (Math.abs(leftParabola.a - rightParabola.a) < Flights.EPSILON)
			centerPoint = -1;
		else
			centerPoint = (rightParabola.b - leftParabola.b) / (2 * (leftParabola.a - rightParabola.a));
		if (centerPoint <= from || centerPoint >= to ||
			fromValue < -Flights.EPSILON && toValue > Flights.EPSILON ||
			fromValue > Flights.EPSILON && toValue < -Flights.EPSILON)
		{
			if (fromValue > -Flights.EPSILON && toValue > -Flights.EPSILON) {
				parts[index + 1] = to;
				parabolas[index++] = leftParabola;
			} else if (fromValue < Flights.EPSILON && toValue < Flights.EPSILON) {
				parts[index + 1] = to;
				parabolas[index++] = rightParabola;
			} else {
				if (fromValue > toValue) {
					Parabola temp = leftParabola;
					leftParabola = rightParabola;
					rightParabola = temp;
				}
				double intersectionPoint = Parabola.intersect(leftParabola, rightParabola, from, to);
				parts[index + 1] = intersectionPoint;
				parabolas[index++] = rightParabola;
				parts[index + 1] = to;
				parabolas[index++] = leftParabola;
			}
		} else {
			double centerValue = leftParabola.value(centerPoint) - rightParabola.value(centerPoint);
			if (fromValue > -Flights.EPSILON && toValue > -Flights.EPSILON && centerValue > -Flights.EPSILON) {
				parts[index + 1] = to;
				parabolas[index++] = leftParabola;
			} else  if (fromValue < Flights.EPSILON && toValue < Flights.EPSILON && centerValue < Flights.EPSILON) {
				parts[index + 1] = to;
				parabolas[index++] = rightParabola;
			} else {
				if (centerValue < 0) {
					Parabola temp = leftParabola;
					leftParabola = rightParabola;
					rightParabola = temp;
				}
				double intersectionPoint = Parabola.intersect(leftParabola, rightParabola, from, centerPoint);
				if (intersectionPoint > from + Flights.EPSILON && intersectionPoint < centerPoint - Flights.EPSILON) {
					parts[index + 1] = intersectionPoint;
					parabolas[index++] = rightParabola;
				}
				intersectionPoint = Parabola.intersect(rightParabola, leftParabola, centerPoint, to);
				if (intersectionPoint > centerPoint + Flights.EPSILON && intersectionPoint < to - Flights.EPSILON) {
					parts[index + 1] = intersectionPoint;
					parabolas[index++] = leftParabola;
				}
				parts[index + 1] = to;
				parabolas[index++] = rightParabola;
			}
		}
		return index;
	}

	}

class Parabola {
	static final Parabola ZERO = new Parabola(0, 1, 0);

	final double a;
	final double b;
	final double c;

	Parabola(double center, double side, int height) {
		double delta = side - center;
		a = -height / (delta * delta);
		b = -center * 2 * a;
		c = height + center * center * a;
	}

	public double value(double point) {
		return a * point * point + b * point + c;
	}

	public static double intersect(Parabola leftParabola, Parabola rightParabola, double from, double to) {
		while (to - from > Flights.EPSILON) {
			double point = (from + to) / 2;
			if (leftParabola.value(point) - rightParabola.value(point) > 0)
				to = point;
			else
				from = point;
		}
		return (from + to) / 2;
	}
}
