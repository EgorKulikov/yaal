package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int[] color;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int height = in.readInt();
		char[][][] frames = new char[count][][];
		for (int i = 0; i < count; i++) {
			int width = in.readInt();
			frames[i] = IOUtils.readTable(in, height, width);
		}
		int[][] leftDelta = new int[count][height];
		int[][] rightDelta = new int[count][height];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < frames[i][j].length; k++) {
					if (frames[i][j][k] == '0')
						leftDelta[i][j]++;
					else
						break;
				}
				for (int k = frames[i][j].length - 1; k >= 0; k--) {
					if (frames[i][j][k] == '0')
						rightDelta[i][j]++;
					else
						break;
				}
			}
		}
		int[][] nextNotFree = new int[count][height];
		for (int i = 0; i < count; i++) {
			int lastNotFree = height;
			for (int j = height - 1; j >= 0; j--) {
				if (leftDelta[i][j] != frames[i][j].length)
					lastNotFree = j;
				nextNotFree[i][j] = lastNotFree;
			}
		}
		int[][] nextLeft = buildNext(count, height, leftDelta);
		int[][] nextRight = buildNext(count, height, rightDelta);
		final int[] time = new int[3 * count];
		Heap nextEvent = new Heap(3 * count, new IntComparator() {
			public int compare(int first, int second) {
				return time[first] - time[second];
			}
		}, 3 * count);
		final int[] curLeft = new int[count];
		Heap left = new Heap(count, new IntComparator() {
			public int compare(int first, int second) {
				return curLeft[second] - curLeft[first];
			}
		}, count);
		final int[] curRight = new int[count];
		Heap right = new Heap(count, new IntComparator() {
			public int compare(int first, int second) {
				return curRight[second] - curRight[first];
			}
		}, count);
		int answer = 0;
		int from = 0;
		int to = 1;
		for (int i = 0; i < height; i++) {
			nextEvent.clear();
			left.clear();
			right.clear();
			int freeWidth = 0;
			for (int j = 2 * count; j < 3 * count; j++) {
				time[j] = nextNotFree[j - 2 * count][i];
				nextEvent.add(j);
				freeWidth += frames[j - 2 * count][i].length;
			}
			int curTime = i;
			while (!nextEvent.isEmpty()) {
				int event = nextEvent.poll();
				int nextTime = time[event];
				if (nextTime != curTime) {
					int curHeight = nextTime - i;
					int candidate = freeWidth * curHeight;
					int leftBest = left.isEmpty() ? -1 : left.peek();
					int rightBest = right.isEmpty() ? -1 : right.peek();
					if (leftBest == rightBest && leftBest != -1) {
						left.poll();
						right.poll();
						int leftSecond = left.isEmpty() ? -1 : left.peek();
						int rightSecond = right.isEmpty() ? -1 : right.peek();
						if (leftSecond == -1) {
							if (rightSecond == -1)
								candidate += Math.max(curLeft[leftBest], curRight[rightBest]) * curHeight;
							else
								candidate += Math.max(curLeft[leftBest] + curRight[rightSecond], curRight[rightBest]) * curHeight;
						} else {
							if (rightSecond == -1)
								candidate += Math.max(curLeft[leftSecond] + curRight[rightBest], curLeft[leftBest]) * curHeight;
							else
								candidate += Math.max((curLeft[leftSecond] + curRight[rightBest]), (curLeft[leftBest] + curRight[rightSecond])) * curHeight;
						}
						left.add(leftBest);
						right.add(rightBest);
					} else {
						if (leftBest != -1)
							candidate += curLeft[leftBest] * curHeight;
						if (rightBest != -1)
							candidate += curRight[rightBest] * curHeight;
					}
					if (candidate > answer) {
						answer = candidate;
						from = i;
						to = nextTime;
					}
					curTime = nextTime;
				}
				if (nextTime == height)
					break;
				if (event >= 2 * count) {
					event -= 2 * count;
					freeWidth -= frames[event][i].length;
					curLeft[event] = leftDelta[event][curTime];
					left.add(event);
					curRight[event] = rightDelta[event][curTime];
					right.add(event);
					time[event] = nextLeft[event][curTime];
					nextEvent.add(event);
					time[event + count] = nextRight[event][curTime];
					nextEvent.add(event + count);
				} else if (event < count) {
					curLeft[event] = leftDelta[event][curTime];
					left.shiftDown(left.getIndex(event));
					time[event] = nextLeft[event][curTime];
					nextEvent.add(event);
				} else {
					event -= count;
					curRight[event] = rightDelta[event][curTime];
					right.shiftDown(right.getIndex(event));
					time[event + count] = nextRight[event][curTime];
					nextEvent.add(event + count);
				}
			}
		}
		int[] permutation = new int[count];
		int firstRow = from + 1;
		int firstColumn = -1;
		int secondRow = to;
		int secondColumn = -1;
		if (answer != 0) {
		nextEvent.clear();
		left.clear();
		right.clear();
		for (int j = 2 * count; j < 3 * count; j++) {
			time[j] = nextNotFree[j - 2 * count][from];
			nextEvent.add(j);
		}
		int curTime = from;
		while (!nextEvent.isEmpty()) {
			int event = nextEvent.poll();
			int nextTime = time[event];
			if (nextTime == to) {
				int leftBest = left.isEmpty() ? -1 : left.peek();
				int rightBest = right.isEmpty() ? -1 : right.peek();
				int selectedRight = rightBest;
				int selectedLeft = leftBest;
				if (leftBest == rightBest && leftBest != -1) {
					left.poll();
					right.poll();
					int leftSecond = left.isEmpty() ? -1 : left.peek();
					int rightSecond = right.isEmpty() ? -1 : right.peek();
					if (leftSecond == -1) {
						if (rightSecond != -1)
							if (curLeft[leftBest] + curRight[rightSecond] > curRight[rightBest])
								selectedRight = rightSecond;
							else
								selectedLeft = -1;
						else {
							if (curLeft[leftBest] > curRight[rightBest])
								selectedRight = -1;
							else
								selectedLeft = -1;
						}
					} else {
						if (rightSecond == -1) {
							if (curRight[rightBest] + curLeft[leftSecond] > curLeft[leftBest])
								selectedLeft = leftSecond;
							else
								selectedRight = -1;
						} else {
							if (curLeft[leftSecond] + curRight[rightBest] > curLeft[leftBest] + curRight[rightSecond])
								selectedLeft = leftSecond;
							else
								selectedRight = rightSecond;
						}
					}
					left.add(leftBest);
					right.add(rightBest);
				}
				boolean[] used = new boolean[count];
				int size = 0;
				if (selectedRight != -1) {
					permutation[size++] = selectedRight + 1;
					firstColumn = frames[selectedRight][0].length - curRight[selectedRight] + 1;
					secondColumn = frames[selectedRight][0].length;
					used[selectedRight] = true;
				} else {
					firstColumn = 1;
					secondColumn = 0;
				}
				if (event >= 2 * count) {
					event -= 2 * count;
					permutation[size++] = event + 1;
					secondColumn += frames[event][0].length;
					used[event] = true;
				}
				while (!nextEvent.isEmpty()) {
					event = nextEvent.poll();
					if (event >= 2 * count) {
						event -= 2 * count;
						permutation[size++] = event + 1;
						secondColumn += frames[event][0].length;
						used[event] = true;
					}
				}
				if (selectedLeft != -1) {
					permutation[size++] = selectedLeft + 1;
					secondColumn += curLeft[selectedLeft];
					used[selectedLeft] = true;
				}
				for (int i = 0; i < count; i++) {
					if (!used[i])
						permutation[size++] = i + 1;
				}
				break;
			}
			if (nextTime == height)
				throw new RuntimeException();
			curTime = nextTime;
			if (event >= 2 * count) {
				event -= 2 * count;
				curLeft[event] = leftDelta[event][curTime];
				left.add(event);
				curRight[event] = rightDelta[event][curTime];
				right.add(event);
				time[event] = nextLeft[event][curTime];
				nextEvent.add(event);
				time[event + count] = nextRight[event][curTime];
				nextEvent.add(event + count);
			} else if (event < count) {
				curLeft[event] = leftDelta[event][curTime];
				left.shiftDown(left.getIndex(event));
				time[event] = nextLeft[event][curTime];
				nextEvent.add(event);
			} else {
				event -= count;
				curRight[event] = rightDelta[event][curTime];
				right.shiftDown(right.getIndex(event));
				time[event + count] = nextRight[event][curTime];
				nextEvent.add(event + count);
			}
		}
		}
		boolean special = false;
		int specialFrame = -1;
		color = new int[height];
		int[] curWidth = new int[height];
		final int[] depth = new int[height];
		Heap heap = new Heap(height, new IntComparator() {
			public int compare(int first, int second) {
				return depth[second] - depth[first];
			}
		}, height);
		for (int i = 0; i < count; i++) {
			int width = frames[i][0].length;
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < height; k++) {
					if (frames[i][k][j] == '0')
						depth[k]++;
					else
						depth[k] = 0;
					heap.add(k);
					color[k] = -1;
				}
				while (!heap.isEmpty()) {
					int k = heap.poll();
					int head = k;
					curWidth[k] = 1;
					color[k] = k;
					if (k > 0 && color[k - 1] != -1) {
						head = get(k - 1);
						color[k] = head;
						curWidth[head]++;
					}
					if (k < height - 1 && color[k + 1] != -1) {
						color[k + 1] = head;
						curWidth[head] += curWidth[k + 1];
					}
					if (curWidth[head] * depth[k] > answer) {
						special = true;
						specialFrame = i;
						answer = curWidth[head] * depth[k];
						firstRow = head + 1;
						secondRow = head + curWidth[head];
						firstColumn = j - depth[k] + 2;
						secondColumn = j + 1;
					}
				}
			}
		}
		if (special) {
			permutation[0] = specialFrame + 1;
			int size = 1;
			for (int i = 0; i < count; i++) {
				if (i != specialFrame)
					permutation[size++] = i + 1;
			}
		}
		if ((secondRow - firstRow + 1) * (secondColumn - firstColumn + 1) != answer)
			throw new RuntimeException();
		out.printLine(permutation);
		out.printLine(firstRow, firstColumn, secondRow, secondColumn);
//		System.err.println(answer);
	}

	private int get(int v) {
		if (color[v] == v)
			return v;
		return color[v] = get(color[v]);
	}

	private int[][] buildNext(int count, int height, int[][] delta) {
		int[][] next = new int[count][height];
		int[] stack = new int[height];
		for (int i = 0; i < count; i++) {
			int stackSize = 0;
			for (int j = height - 1; j >= 0; j--) {
				while (stackSize > 0 && delta[i][stack[stackSize - 1]] >= delta[i][j])
					stackSize--;
				if (stackSize > 0)
					next[i][j] = stack[stackSize - 1];
				else
					next[i][j] = height;
				stack[stackSize++] = j;
			}
		}
		return next;
	}
}
