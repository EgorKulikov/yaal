package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		in.readInt();
		int[] height = new int[count];
		int[] left = new int[count];
		int[] right = new int[count];
		IOUtils.readIntArrays(in, height, left, right);
		int[] order = ArrayUtils.order(height);
		NavigableSet<Segment> segments = new TreeSet<Segment>();
		segments.add(new Segment((int)(-1e9 - 1), (int)(1e9 + 1), (int) 2e9, (int)(-1e9 - 1), (int)(1e9 + 1)));
		for (int i : order) {
			Segment leftFake = new Segment(left[i], 0, 0, 0, 0);
			Segment rightFake = new Segment(right[i], 0, 0, 0, 0);
			Segment toLeft = segments.floor(leftFake);
			Segment toRight = segments.lower(rightFake);
			if (toLeft == toRight) {
				segments.remove(toLeft);
				if (left[i] > toLeft.left)
					segments.add(new Segment(toLeft.left, left[i], toLeft.value, toLeft.wholeLeft, toLeft.wholeRight));
				if (right[i] < toLeft.right)
					segments.add(new Segment(right[i], toLeft.right, toLeft.value, toLeft.wholeLeft, toLeft.wholeRight));
				segments.add(new Segment(left[i], right[i], Math.min(right[i] - left[i], toLeft.value), left[i], right[i]));
				continue;
			}
			segments.remove(toLeft);
			segments.remove(toRight);
			int currentResult = 0;
			if (toLeft.right == toLeft.wholeRight)
				currentResult = Math.max(currentResult, Math.min(toLeft.right - left[i], toLeft.value));
			if (left[i] > toLeft.left)
				segments.add(new Segment(toLeft.left, left[i], toLeft.value, toLeft.wholeLeft, toLeft.wholeRight));
			if (toRight.left == toRight.wholeLeft)
				currentResult = Math.max(currentResult, Math.min(right[i] - toRight.left, toRight.value));
			if (right[i] < toRight.right)
				segments.add(new Segment(right[i], toRight.right, toRight.value, toRight.wholeLeft, toRight.wholeRight));
			Iterator<Segment> iterator = segments.subSet(leftFake, true, rightFake, false).iterator();
			while (iterator.hasNext()) {
				Segment segment = iterator.next();
				iterator.remove();
				if (segment.left == segment.wholeLeft && segment.right == segment.wholeRight)
					currentResult = Math.max(currentResult, Math.min(segment.right - segment.left, segment.value));
			}
			segments.add(new Segment(left[i], right[i], currentResult, left[i], right[i]));
		}
		int answer = 0;
		for (Segment segment : segments) {
			if (segment.wholeLeft == segment.left && segment.wholeRight == segment.right)
				answer = Math.max(answer, segment.value);
		}
		out.printLine(answer);
	}

	static class Segment implements Comparable<Segment> {
		final int left, right, wholeLeft, wholeRight, value;

		Segment(int left, int right, int value, int wholeLeft, int wholeRight) {
			this.left = left;
			this.right = right;
			this.value = value;
			this.wholeLeft = wholeLeft;
			this.wholeRight = wholeRight;
		}

		public int compareTo(Segment o) {
			if (left < o.left)
				return -1;
			if (left > o.left)
				return 1;
			return 0;
		}
	}
}
