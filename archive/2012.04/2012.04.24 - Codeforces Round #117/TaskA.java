package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int walkDistance = in.readInt();
		int additionalTime = in.readInt();
		int ax = in.readInt();
		int ay = in.readInt();
		int bx = in.readInt();
		int by = in.readInt();
		int squaredWalkDistance = walkDistance * walkDistance;
		if (squaredDistance(ax, ay, bx, by) <= squaredWalkDistance) {
			out.printLine(Math.hypot(ax - bx, ay - by));
			return;
		}
		int count = in.readInt();
		int[] x1 = new int[count];
		int[] y1 = new int[count];
		int[] x2 = new int[count];
		int[] y2 = new int[count];
		IOUtils.readIntArrays(in, x1, y1, x2, y2);
		for (int i = 0; i < count; i++) {
			if (x1[i] == x2[i] && y1[i] > y2[i]) {
				int temp = y1[i];
				y1[i] = y2[i];
				y2[i] = temp;
			} else if (y1[i] == y2[i] && x1[i] > x2[i]) {
				int temp = x1[i];
				x1[i] = x2[i];
				x2[i] = temp;
			}
		}
		boolean[] accessibleFromStart = new boolean[count];
		boolean[] accessibleFromFinish = new boolean[count];
		boolean[][] canMove = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			accessibleFromStart[i] = squaredDistanceToSegment(ax, ay, x1[i], y1[i], x2[i], y2[i]) <= squaredWalkDistance;
			accessibleFromFinish[i] = squaredDistanceToSegment(bx, by, x1[i], y1[i], x2[i], y2[i]) <= squaredWalkDistance;
			for (int j = i + 1; j < count; j++)
				canMove[j][i] = canMove[i][j] = squaredDistanceBetweenSegments(x1[i], y1[i], x2[i], y2[i], x1[j], y1[j], x2[j], y2[j]) <= squaredWalkDistance;
		}
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int[] answer = new int[count];
		Arrays.fill(answer, Integer.MAX_VALUE);
		for (int i = 0; i < count; i++) {
			if (accessibleFromStart[i]) {
				answer[i] = 1;
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int i = 0; i < count; i++) {
				if (answer[i] == Integer.MAX_VALUE && canMove[current][i]) {
					answer[i] = answer[current] + 1;
					queue.add(i);
				}
			}
		}
		double result = Double.POSITIVE_INFINITY;
		for (int i = 0; i < count; i++) {
			if (accessibleFromFinish[i] && answer[i] != Integer.MAX_VALUE)
				result = Math.min(result, answer[i] * (walkDistance + additionalTime) + Math.sqrt(squaredDistanceToSegment(bx, by, x1[i], y1[i], x2[i], y2[i])));
		}
		if (result == Double.POSITIVE_INFINITY)
			out.printLine(-1);
		else
			out.printLine(result);
	}

	private int squaredDistanceBetweenSegments(int x1, int y1, int x2, int y2, int xx1, int yy1, int xx2, int yy2) {
		return Math.min(Math.min(squaredDistanceToSegment(x1, y1, xx1, yy1, xx2, yy2), squaredDistanceToSegment(x2, y2, xx1, yy1, xx2, yy2)),
			Math.min(squaredDistanceToSegment(xx1, yy1, x1, y1, x2, y2), squaredDistanceToSegment(xx2, yy2, x1, y1, x2, y2)));
	}

	private int squaredDistanceToSegment(int x, int y, int x1, int y1, int x2, int y2) {
		int result = Math.min(squaredDistance(x, y, x1, y1), squaredDistance(x, y, x2, y2));
		if (x1 == x2 && y1 <= y && y <= y2)
			result = Math.min(result, Math.abs(x - x1) * Math.abs(x - x1));
		else if (y1 == y2 && x1 <= x && x <= x2)
			result = Math.min(result, Math.abs(y - y1) * Math.abs(y - y1));
		return result;
	}

	private int squaredDistance(int ax, int ay, int bx, int by) {
		return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
	}
}
