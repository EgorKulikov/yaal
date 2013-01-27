package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Covered {
	private int additional;
	private long[] answer;
	private long[] points;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		additional = in.readInt();
		if (count == 0)
			throw new UnknownError();
		points = new long[count];
		for (int i = 0; i < count; i++)
			points[i] = in.readInt();
		answer = new long[count + 1];
		Parabola[] stack = new Parabola[count];
		double[] intersect = new double[count];
		stack[0] = new Parabola(-2 * points[0], sqr(points[0]) + additional);
		int size = 1;
		for (int i = 0; i < count; i++) {
			int at;
			if (size == 1)
				at = 0;
			else
				at = Arrays.binarySearch(intersect, 0, size - 1, points[i]);
			if (at < 0)
				at = -at - 1;
			answer[i + 1] = stack[at].value(points[i]);
			if (i + 1 == count)
				break;
			Parabola next = new Parabola(-2 * points[i + 1], sqr(points[i + 1]) + additional + answer[i + 1]);
			while (size > 1) {
				double oldValue = stack[size - 1].value(intersect[size - 2]);
				double newValue = next.value(intersect[size - 2]);
				if (newValue < oldValue)
					size--;
				else
					break;
			}
			stack[size] = next;
			intersect[size - 1] = next.intersect(stack[size - 1]);
			size++;
		}
		out.printLine(answer[count]);
	}

	private long value(int at, int right) {
		return answer[at] + sqr(points[at] - points[right]) + additional;
	}

	private long sqr(long value) {
		return value * value;
	}
}

class Parabola {
	long p, q;

	Parabola(long p, long q) {
		this.p = p;
		this.q = q;
	}

	long value(long x) {
		return x * x + p * x + q;
	}

	double value(double x) {
		return x * x + p * x + q;
	}

	double intersect(Parabola other) {
		long pp = p - other.p;
		long qq = other.q - q;
		return (double)qq / pp;
	}
}