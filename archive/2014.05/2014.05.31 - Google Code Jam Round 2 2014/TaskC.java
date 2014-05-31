package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskC {
	int[] result;
	AtomicInteger solved = new AtomicInteger(0);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int testCount = in.readInt();
		result = new int[testCount];
		for (int i = 0; i < testCount; i++) {
			int width = in.readInt();
			int height = in.readInt();
			int count = in.readInt();
			int[] x0 = new int[count];
			int[] y0 = new int[count];
			int[] x1 = new int[count];
			int[] y1 = new int[count];
			IOUtils.readIntArrays(in, x0, y0, x1, y1);
			x0 = Arrays.copyOf(x0, count + 1);
			x1 = Arrays.copyOf(x1, count + 1);
			y0 = Arrays.copyOf(y0, count + 1);
			y1 = Arrays.copyOf(y1, count + 1);
			y1[count] = height - 1;
			new Thread(new Solver(i, width, height, count, x0, y0, x1, y1)).start();
		}
		while (solved.get() != testCount) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < testCount; i++)
			out.printLine("Case #" + (i + 1) + ":", result[i]);
    }

	class Solver implements Runnable {
		int id;
		int width;
		int height;
		int count;
		int[] x0;
		int[] y0;
		int[] x1;
		int[] y1;

		Solver(int id, int width, int height, int count, int[] x0, int[] y0, int[] x1, int[] y1) {
			this.id = id;
			this.width = width;
			this.height = height;
			this.count = count;
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}

		@Override
		public void run() {
			int answer = 0;
			boolean[] enlarge = new boolean[count + 1];
			x1[count] = -1;
			for (int i = 0; i < width; i++) {
				Arrays.fill(enlarge, false);
				int dx = 0;
				int dy = 1;
				int x = i;
				int y = 0;
				int current = -1;
				for (int j = 0; j <= count; j++) {
					if (x1[j] == i - 1 && y0[j] == 0) {
						current = j;
						break;
					}
				}
				if (current == -1)
					continue;
				while (y != height && y != -1 && x != width) {
					enlarge[current] = true;
					int next = -1;
					for (int j = 0; j <= count; j++) {
						if (x0[j] <= x && x <= x1[j] && y0[j] <= y && y <= y1[j]) {
							next = -2;
							break;
						}
						if (dy != 0 && x0[j] <= x && x <= x1[j]) {
							if (dy == 1 && y < y0[j]) {
								if (next == -1 || y0[j] < y0[next])
									next = j;
							} else if (dy == -1 && y > y1[j]) {
								if (next == -1 || y1[j] > y1[next])
									next = j;
							}
						} else if (dx != 0 && y0[j] <= y && y <= y1[j]) {
							if (dx == 1 && x < x0[j]) {
								if (next == -1 || x0[j] < x0[next])
									next = j;
							} else if (dx == -1 && x > x1[j]) {
								if (next == -1 || x1[j] > x1[next])
									next = j;
							}
						}
					}
					if (next == -2)
						break;
					if (next == -1 || dy == 1 && y0[next] > y1[current] + 1 || dy == -1 && y1[next] < y0[current] - 1 ||
						dx == 1 && x0[next] > x1[current] + 1 || dx == -1 && x1[next] < x0[current] - 1)
					{
						if (dy == 1)
							y = y1[current] + 1;
						if (dy == -1)
							y = y0[current] - 1;
						if (dx == 1)
							x = x1[current] + 1;
						if (dx == -1)
							x = x0[current] - 1;
						int ndx = -dy;
						dy = dx;
						dx = ndx;
					} else {
						if (dy == 1)
							y = y0[next] - 1;
						if (dy == -1)
							y = y1[next] + 1;
						if (dx == 1)
							x = x0[next] - 1;
						if (dx == -1)
							x = x1[next] + 1;
						current = next;
						int ndx = dy;
						dy = -dx;
						dx = ndx;
					}
				}
				if (y == height) {
					answer++;
					for (int j = 0; j <= count; j++) {
						if (enlarge[j]) {
							x0[j] = Math.max(x0[j] - 1, 0);
							y0[j] = Math.max(y0[j] - 1, 0);
							x1[j] = Math.min(x1[j] + 1, width - 1);
							y1[j] = Math.min(y1[j] + 1, height - 1);
						}
					}
				}
			}
			result[id] = answer;
			solved.incrementAndGet();
			System.err.println(id);
		}
	}
}
