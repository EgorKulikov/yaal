package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		List<Segment> answer = new ArrayList<Segment>();
		int rightIndex = 0;
		int leftIndex = count - 1;
		int minY = CollectionUtils.minElement(Array.wrap(y));
		for (int i = y[0]; i > minY; i--) {
			if (i == y[rightIndex + 1])
				rightIndex++;
			if (i == y[leftIndex])
				leftIndex--;
			long a = y[rightIndex + 1] - y[rightIndex];
			long b = x[rightIndex] - x[rightIndex + 1];
			long c = -x[rightIndex] * a - y[rightIndex] * b;
			if (a < 0) {
				a = -a;
				b = -b;
				c = -c;
			}
			long lastX = new Rational(-b * i - c - 1, a).floor();
			a = y[(leftIndex + 1) % count] - y[leftIndex];
			b = x[leftIndex] - x[(leftIndex + 1) % count];
			c = -x[leftIndex] * a - y[leftIndex] * b;
			long firstX = new Rational(-b * i - c + 1, a).ceil();
			if (firstX <= lastX && i != y[0])
				answer.add(new Segment(i, firstX, lastX));
		}
		out.printLine(testNumber, answer.size());
		for (Segment segment : answer) {
			out.printLine(segment.y, segment.x0, segment.x1);
		}
	}
}

class Segment {
	final int y;
	final long x0;
	final long x1;

	Segment(int y, long x0, long x1) {
		this.y = y;
		this.x0 = x0;
		this.x1 = x1;
	}
}