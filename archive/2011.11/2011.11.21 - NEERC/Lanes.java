package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableMap;
import java.util.TreeMap;

public class Lanes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int leftToRight = in.readInt();
		int rightToLeft = in.readInt();
		int intervalCount = in.readInt();
		int downTime = in.readInt();
		int[] carLeft = new int[intervalCount];
		int[] carRight = new int[intervalCount];
		for (int i = 0; i < intervalCount; i++) {
			carLeft[i] = in.readInt();
			carRight[i] = in.readInt();
		}
		int rightWait = 0;
		MultiSet right = new MultiSet();
		int[] passRight = new int[intervalCount + downTime];
		long totalWait = 0;
		for (int i = 0; i < intervalCount + downTime; i++) {
			if (i < intervalCount)
				rightWait += carRight[i];
			passRight[i] = Math.min(rightWait, rightToLeft + 1);
			rightWait -= rightToLeft + 1;
			if (rightWait < 0) {
				right.add(i, -rightWait);
				rightWait = 0;
			} else
				totalWait += rightWait;
		}
		int time = intervalCount + downTime;
		int delta = rightWait / (rightToLeft + 1);
		totalWait += (long)delta * (delta - 1) / 2 * (rightToLeft + 1);
		time += delta;
		rightWait %= rightToLeft + 1;
		totalWait += (long)delta * rightWait;
		while (right.size() < intervalCount + downTime) {
			right.add(time++, rightToLeft + 1 - rightWait);
			rightWait = 0;
		}
		long[] rightWaitAfter = new long[intervalCount + downTime];
		rightWaitAfter[0] = totalWait;
		for (int i = 1; i < intervalCount + downTime; i++) {
			if (passRight[i - 1] != rightToLeft + 1) {
				rightWaitAfter[i] = rightWaitAfter[i - 1];
				right.remove(i - 1);
			} else {
				int index = right.poll();
				rightWaitAfter[i] = rightWaitAfter[i - 1] + index - i + 1;
				if (index < intervalCount + downTime)
					passRight[index]++;
			}
		}
		long[] leftWaitAfter = new long[intervalCount];
		int[] passLeft = new int[intervalCount];
		int leftWait = 0;
		long leftTotalWait = 0;
		for (int i = 0; i < intervalCount; i++) {
			leftWait += carLeft[i];
			passLeft[i] = Math.min(leftWait, leftToRight + 1);
			leftWait = Math.max(0, leftWait - leftToRight - 1);
			leftTotalWait += leftWait;
		}
		MultiSet left = new MultiSet();
		time = intervalCount;
		delta = leftWait / leftToRight;
		leftTotalWait += (long)delta * (delta - 1) / 2 * (leftToRight);
		time += delta;
		leftWait %= leftToRight;
		leftTotalWait += (long)delta * leftWait;
		while (left.size() < intervalCount) {
			left.add(time++, leftToRight - leftWait);
			leftWait = 0;
		}
		for (int i = intervalCount - 1; i >= 0; i--) {
			if (passLeft[i] == leftToRight + 1) {
				leftTotalWait += left.poll() - i;
			} else {
				passLeft[i]++;
				if (passLeft[i] != leftToRight + 1)
					left.add(i, leftToRight + 1 - passLeft[i]);
			}
			leftWaitAfter[i] = leftTotalWait;
		}
		long best = Long.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < intervalCount; i++) {
			long current = leftWaitAfter[i] + rightWaitAfter[i + downTime];
			if (current < best) {
				best = current;
				index = i;
			}
		}
		out.printLine(index + 1);
//		System.err.println(best + " " + leftWaitAfter[index] + " " + rightWaitAfter[index + downTime]);
		long r = 0;
		long rd = 0;
		for (int i = 0; i < intervalCount || rd != 0; i++) {
			if (i < intervalCount)
				rd += carRight[i];
			if (i < index + downTime)
				rd -= rightToLeft;
			else
				rd -= rightToLeft + 1;
			rd = Math.max(rd, 0);
			r += rd;
		}
		if (r != rightWaitAfter[index + downTime])
			throw new RuntimeException();
		long l = 0;
		long ld = 0;
		for (int i = 0; i < intervalCount || ld != 0; i++) {
			if (i < intervalCount)
				ld += carLeft[i];
			if (i < index)
				ld -= leftToRight + 1;
			else
				ld -= leftToRight;
			ld = Math.max(ld, 0);
			l += ld;
		}
		if (l != leftWaitAfter[index])
			throw new RuntimeException();
	}
}

class MultiSet {
	NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

	public void add(int number, int count) {
		map.put(number, count);
	}

	public int poll() {
		int result = map.firstKey();
		if (map.get(result) == 1)
			map.remove(result);
		else
			map.put(result, map.get(result) - 1);
		return result;
	}

	public int size() {
		return map.size();
	}

	public void remove(int i) {
		map.remove(i);
	}
}
