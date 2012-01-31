package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int d = in.readInt();
		int l = in.readInt();
		int[] x = new int[n];
		for (int i = 1; i < n; ++i)
			x[i] = in.readInt();
		int[] z = new int[m];
		for (int i = 0; i < m; ++i)
			z[i] = in.readInt();
		int[] p = new int[d];
		int[] r = new int[d];
		for (int i = 0; i < d; ++i) {
			p[i] = in.readInt();
			r[i] = in.readInt();
		}
		int[] interestingCoord = new int[2 * d + 2 * m + n];
		int pos = 0;
		for (int i = 0; i < n; ++i) {
			interestingCoord[pos++] = x[i];
		}
		for (int i = 0; i < m; ++i) {
			interestingCoord[pos++] = z[i] - l;
			interestingCoord[pos++] = z[i] + l;
		}
		for (int i = 0; i < d; ++i) {
			interestingCoord[pos++] = r[i] - l;
			interestingCoord[pos++] = r[i] + l;
		}
		if (pos != interestingCoord.length) throw new RuntimeException();
		Arrays.sort(interestingCoord);
		pos = 0;
		for (int i = 0; i < interestingCoord.length; ++i) {
			if (i == 0 || interestingCoord[i] != interestingCoord[i - 1])
				interestingCoord[pos++] = interestingCoord[i];
		}
		int[] cntGopher = new int[pos];
		boolean[] haveCD = new boolean[pos];
		int[] nextCD = new int[pos + 1];
		int[] prevCD = new int[pos + 1];
		nextCD[pos] = pos;
		prevCD[pos] = pos;
		int[] cntCD = new int[pos];
		for (int i = 0; i < n; ++i) {
			update(cntGopher, decodePos(interestingCoord, pos, x[i]), 1);
		}
		int res = 0;
		for (int i = 0; i < m; ++i) {
			int leftPos = decodePos(interestingCoord, pos, z[i] - l);
			int rightPos = decodePos(interestingCoord, pos, z[i] + l);
			int prevCDPos = getLast(cntCD, haveCD, rightPos);
			if (prevCDPos == -1)
				prevCDPos = pos;
			int nextCDPos = nextCD[prevCDPos];
			int finalLeft = leftPos;
			int finalRight = rightPos;
			if (prevCDPos != pos)
				finalLeft = Math.max(finalLeft, prevCDPos + 1);
			if (nextCDPos != pos) {
				int nextCDLeft = decodePos(interestingCoord, pos, interestingCoord[nextCDPos] - 2 * l);
				finalRight = Math.min(finalRight, nextCDLeft - 1);
			}
			if (finalLeft <= finalRight) {
				res += get(cntGopher, finalRight) - get(cntGopher, finalLeft - 1);
			}
			nextCD[rightPos] = nextCDPos;
			prevCD[rightPos] = prevCDPos;
			nextCD[prevCDPos] = rightPos;
			prevCD[nextCDPos] = rightPos;
			if (haveCD[rightPos]) throw new RuntimeException();
			haveCD[rightPos] = true;
			update(cntCD, rightPos, 1);
		}
		out.printLine(res);
		for (int i = 0; i < d; ++i) {
			{
				int leftPos = decodePos(interestingCoord, pos, p[i] - l);
				int rightPos = decodePos(interestingCoord, pos, p[i] + l);
				if (!haveCD[rightPos]) throw new RuntimeException();
				int prevCDPos = prevCD[rightPos];
				int nextCDPos = nextCD[rightPos];
				int finalLeft = leftPos;
				int finalRight = rightPos;
				if (prevCDPos != pos)
					finalLeft = Math.max(finalLeft, prevCDPos + 1);
				if (nextCDPos != pos) {
					int nextCDLeft = decodePos(interestingCoord, pos, interestingCoord[nextCDPos] - 2 * l);
					finalRight = Math.min(finalRight, nextCDLeft - 1);
				}
				if (finalLeft <= finalRight) {
					res -= get(cntGopher, finalRight) - get(cntGopher, finalLeft - 1);
				}
				nextCD[prevCDPos] = nextCDPos;
				prevCD[nextCDPos] = prevCDPos;
				haveCD[rightPos] = false;
				update(cntCD, rightPos, -1);
			}
			{
				int leftPos = decodePos(interestingCoord, pos, r[i] - l);
				int rightPos = decodePos(interestingCoord, pos, r[i] + l);
				int prevCDPos = getLast(cntCD, haveCD, rightPos);
				if (prevCDPos == -1)
					prevCDPos = pos;
				int nextCDPos = nextCD[prevCDPos];
				int finalLeft = leftPos;
				int finalRight = rightPos;
				if (prevCDPos != pos)
					finalLeft = Math.max(finalLeft, prevCDPos + 1);
				if (nextCDPos != pos) {
					int nextCDLeft = decodePos(interestingCoord, pos, interestingCoord[nextCDPos] - 2 * l);
					finalRight = Math.min(finalRight, nextCDLeft - 1);
				}
				if (finalLeft <= finalRight) {
					res += get(cntGopher, finalRight) - get(cntGopher, finalLeft - 1);
				}
				nextCD[rightPos] = nextCDPos;
				prevCD[rightPos] = prevCDPos;
				nextCD[prevCDPos] = rightPos;
				prevCD[nextCDPos] = rightPos;
				if (haveCD[rightPos]) throw new RuntimeException();
				haveCD[rightPos] = true;
				update(cntCD, rightPos, 1);
			}
			out.printLine(res);
		}
	}

	private int get(int[] cntGopher, int at) {
		int res = 0;
		while (at >= 0) {
			res += cntGopher[at];
			at = (at & (at + 1)) - 1;
		}
		return res;
	}

	private int getLast(int[] cntCD, boolean[] haveCD, int at) {
		while (at >= 0) {
			if (haveCD[at]) return at;
			if (cntCD[at] == 0)
				at = (at & (at + 1)) - 1;
			else
				--at;
		}
		return -1;
	}

	private void update(int[] cntGopher, int at, int by) {
		while (at < cntGopher.length) {
			cntGopher[at] += by;
			at |= (at + 1);
		}
	}

	private int decodePos(int[] interestingCoord, int pos, int coord) {
		int l = -1;
		int r = pos;
		while (r - l > 1) {
			int m = (l + r) / 2;
			int middle = interestingCoord[m];
			if (middle == coord)
				return m;
			else if (middle < coord)
				l = m;
			else
				r = m;
		}
		throw new RuntimeException();
	}
}
