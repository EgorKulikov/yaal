package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskC {
	private long[] x;
	private long[] y;
	private char[] type;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		x = new long[count];
		y = new long[count];
		type = new char[count];
		for (int i = 0; i < count; i++) {
			x[i] = in.readLong();
			y[i] = in.readLong();
			type[i] = in.readCharacter();
		}
		Set<Part> parts = new HashSet<Part>();
		parts.add(new Part((long)-1e17, (long)1e17, (long)-1e17, (long)1e17));
		for (int i = 0; i < count; i++) {
			long sum = x[i] + y[i];
			long difference = x[i] - y[i];
			Set<Part> nextParts = new HashSet<Part>();
			for (Part part : parts) {
				if (sum <= part.minSum) {
					if (difference <= part.minDifference)
						checkXConstraint(part, i, nextParts);
					else if (difference < part.maxDifference) {
						if (checkBoth(part, i))
							nextParts.add(part);
						else {
							checkYConstraint(new Part(part, part.minSum, part.maxSum, part.minDifference, difference), i, nextParts);
							checkXConstraint(new Part(part, part.minSum, part.maxSum, difference, part.maxDifference), i, nextParts);
						}
					} else
						checkYConstraint(part, i, nextParts);
				} else if (sum < part.maxSum) {
					if (difference <= part.minDifference) {
						if (checkBoth(part, i))
							nextParts.add(part);
						else {
							checkYConstraint(new Part(part, part.minSum, sum, part.minDifference, part.maxDifference), i, nextParts);
							checkXConstraint(new Part(part, sum, part.maxSum, part.minDifference, part.maxDifference), i, nextParts);
						}
					} else if (difference < part.maxDifference) {
						if (checkBoth(part, i))
							nextParts.add(part);
						else {
							checkXConstraint(new Part(part, part.minSum, sum, part.minDifference, difference), i, nextParts);
							checkYConstraint(new Part(part, part.minSum, sum, difference, part.maxDifference), i, nextParts);
							checkYConstraint(new Part(part, sum, part.maxSum, part.minDifference, difference), i, nextParts);
							checkXConstraint(new Part(part, sum, part.maxSum, difference, part.maxDifference), i, nextParts);
						}
					} else {
						if (checkBoth(part, i))
							nextParts.add(part);
						else {
							checkXConstraint(new Part(part, part.minSum, sum, part.minDifference, part.maxDifference), i, nextParts);
							checkYConstraint(new Part(part, sum, part.maxSum, part.minDifference, part.maxDifference), i, nextParts);
						}
					}
				} else {
					if (difference <= part.minDifference)
						checkYConstraint(part, i, nextParts);
					else if (difference < part.maxDifference) {
						if (checkBoth(part, i))
							nextParts.add(part);
						else {
							checkXConstraint(new Part(part, part.minSum, part.maxSum, part.minDifference, difference), i, nextParts);
							checkYConstraint(new Part(part, part.minSum, part.maxSum, difference, part.maxDifference), i, nextParts);
						}
					} else
						checkXConstraint(part, i, nextParts);
				}
			}
			parts = nextParts;
		}
		if (parts.isEmpty()) {
			out.printLine("Case #" + testNumber + ": Too damaged");
			return;
		}
		long ax = Long.MAX_VALUE / 2;
		long ay = Long.MAX_VALUE / 2;
		long minDistance = ax + ay;
		Set<Part> nextParts = new HashSet<Part>();
		long sum = 0;
		long difference = 0;
		for (Part part : parts) {
			if (0 <= part.minSum) {
				if (0 <= part.minDifference)
					nextParts.add(part);
				else if (0 < part.maxDifference) {
					nextParts.add(new Part(part, part.minSum, part.maxSum, part.minDifference, difference));
					nextParts.add(new Part(part, part.minSum, part.maxSum, difference, part.maxDifference));
				} else
					nextParts.add(part);
			} else if (sum < part.maxSum) {
				if (difference <= part.minDifference) {
					nextParts.add(new Part(part, part.minSum, sum, part.minDifference, part.maxDifference));
					nextParts.add(new Part(part, sum, part.maxSum, part.minDifference, part.maxDifference));
				} else if (difference < part.maxDifference) {
					nextParts.add(new Part(part, part.minSum, sum, part.minDifference, difference));
					nextParts.add(new Part(part, part.minSum, sum, difference, part.maxDifference));
					nextParts.add(new Part(part, sum, part.maxSum, part.minDifference, difference));
					nextParts.add(new Part(part, sum, part.maxSum, difference, part.maxDifference));
				} else {
					nextParts.add(new Part(part, part.minSum, sum, part.minDifference, part.maxDifference));
					nextParts.add(new Part(part, sum, part.maxSum, part.minDifference, part.maxDifference));
				}
			} else {
				if (difference <= part.minDifference)
					nextParts.add(part);
				else if (difference < part.maxDifference) {
					nextParts.add(new Part(part, part.minSum, part.maxSum, part.minDifference, difference));
					nextParts.add(new Part(part, part.minSum, part.maxSum, difference, part.maxDifference));
				} else
					nextParts.add(part);
			}
		}
		parts = nextParts;
		long[] xx = new long[12];
		long[] yy = new long[12];
		for (Part part : parts) {
			xx[0] = (part.minSum + part.minDifference) / 2;
			yy[0] = part.minSum - xx[0];
			xx[1] = (part.minSum + part.maxDifference) / 2;
			yy[1] = part.minSum - xx[1];
			xx[2] = (part.maxSum + part.minDifference) / 2;
			yy[2] = part.maxSum - xx[2];
			xx[3] = (part.maxSum + part.maxDifference) / 2;
			yy[3] = part.maxSum - xx[3];
			xx[4] = 0;
			yy[4] = part.minSum;
			xx[5] = 0;
			yy[5] = part.maxSum;
			xx[6] = part.minSum;
			yy[6] = 0;
			xx[7] = part.maxSum;
			yy[7] = 0;
			xx[8] = 0;
			yy[8] = -part.minDifference;
			xx[9] = 0;
			yy[9] = -part.maxDifference;
			xx[10] = part.minDifference;
			yy[10] = 0;
			xx[11] = part.maxDifference;
			yy[11] = 0;
			for (int i = 0; i < 12; i++) {
				for (long cx = xx[i] - 5; cx <= xx[i] + 5; cx++) {
					for (long cy = yy[i] - 5; cy <= yy[i] + 5; cy++) {
						if (part.inside(cx, cy) && part.xConstraint.filter(cx) && part.yConstraint.filter(cy)) {
							long distance = Math.abs(cx) + Math.abs(cy);
							if (distance < minDistance || distance == minDistance && (cx > ax || cx == ax && cy > ay)) {
								minDistance = distance;
								ax = cx;
								ay = cy;
							}
						}
					}
				}
			}
		}
		out.printLine("Case #" + testNumber + ":", ax, ay);
	}

	private boolean checkBoth(Part part, int i) {
		Constraint xConstraint;
		if (x[i] % 2 == 0 ^ type[i] == '#')
			xConstraint = Constraint.EVEN;
		else
			xConstraint = Constraint.ODD;
		Constraint yConstraint;
		if (y[i] % 2 == 0 ^ type[i] == '#')
			yConstraint = Constraint.EVEN;
		else
			yConstraint = Constraint.ODD;
		return part.xConstraint == xConstraint && part.yConstraint == yConstraint;
	}

	private void checkXConstraint(Part part, int i, Set<Part> parts) {
		Constraint xConstraint;
		if (x[i] % 2 == 0 ^ type[i] == '#')
			xConstraint = Constraint.EVEN;
		else
			xConstraint = Constraint.ODD;
		if (part.xConstraint == Constraint.ANY)
			part.xConstraint = xConstraint;
		if (part.xConstraint == xConstraint)
			parts.add(part);
	}

	private void checkYConstraint(Part part, int i, Set<Part> parts) {
		Constraint yConstraint;
		if (y[i] % 2 == 0 ^ type[i] == '#')
			yConstraint = Constraint.EVEN;
		else
			yConstraint = Constraint.ODD;
		if (part.yConstraint == Constraint.ANY)
			part.yConstraint = yConstraint;
		if (part.yConstraint == yConstraint)
			parts.add(part);
	}

	static class Part {
		final long minSum;
		final long maxSum;
		final long minDifference;
		final long maxDifference;
		Constraint xConstraint = Constraint.ANY;
		Constraint yConstraint = Constraint.ANY;

		Part(long minSum, long maxSum, long minDifference, long maxDifference) {
			this.minSum = minSum;
			this.maxSum = maxSum;
			this.minDifference = minDifference;
			this.maxDifference = maxDifference;
		}

		Part(Part part, long minSum, long maxSum, long minDifference, long maxDifference) {
			this.minSum = minSum;
			this.maxSum = maxSum;
			this.minDifference = minDifference;
			this.maxDifference = maxDifference;
			xConstraint = part.xConstraint;
			yConstraint = part.yConstraint;
		}

		boolean inside(long x, long y) {
			long sum = x + y;
			long difference = x - y;
			return sum >= minSum && sum <= maxSum && difference >= minDifference && difference <= maxDifference;
		}
	}

	enum Constraint {
		ANY, ODD, EVEN;

		boolean filter(long value) {
			if (this == ANY)
				return true;
			if (this == EVEN)
				return value % 2 == 0;
			return value % 2 != 0;
		}
	}
}
