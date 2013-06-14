package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count + 1];
		int[] y = new int[count + 1];
		for (int i = 0; i < count; i++) {
			char direction = in.readCharacter();
			int delta = in.readInt();
			x[i + 1] = x[i];
			y[i + 1] = y[i];
			if (direction == 'U')
				y[i + 1] -= delta;
			else if (direction == 'D')
				y[i + 1] += delta;
			else if (direction == 'L')
				x[i + 1] -= delta;
			else
				x[i + 1] += delta;
		}
		int[] ux = new int[3 * x.length];
		for (int i = 0; i < x.length; i++) {
			ux[3 * i] = x[i];
			ux[3 * i + 1] = x[i] - 1;
			ux[3 * i + 2] = x[i] + 1;
		}
		Arrays.sort(ux);
		ux = ArrayUtils.unique(ux);
		int[] uy = new int[3 * y.length];
		for (int i = 0; i < y.length; i++) {
			uy[3 * i] = y[i];
			uy[3 * i + 1] = y[i] - 1;
			uy[3 * i + 2] = y[i] + 1;
		}
		Arrays.sort(uy);
		uy = ArrayUtils.unique(uy);
		boolean[][] map = new boolean[ux.length + 1][uy.length + 1];
		for (int i = 0; i < count; i++) {
			if (x[i] == x[i + 1]) {
				int index = Arrays.binarySearch(ux, x[i]);
				int from = Arrays.binarySearch(uy, y[i]);
				int to = Arrays.binarySearch(uy, y[i + 1]);
				if (from > to) {
					int temp = from;
					from = to;
					to = temp;
				}
				for (int j = from; j <= to; j++)
					map[index][j] = true;
			} else {
				int index = Arrays.binarySearch(uy, y[i]);
				int from = Arrays.binarySearch(ux, x[i]);
				int to = Arrays.binarySearch(ux, x[i + 1]);
				if (from > to) {
					int temp = from;
					from = to;
					to = temp;
				}
				for (int j = from; j <= to; j++)
					map[j][index] = true;
			}
		}
		int[] xQueue = new int[map.length * map[0].length];
		int[] yQueue = new int[map.length * map[0].length];
		int size = 1;
		boolean[][] visited = new boolean[map.length][map[0].length];
		visited[0][0] = true;
		for (int i = 0; i < size; i++) {
			int xx = xQueue[i];
			int yy = yQueue[i];
			for (int j = 0; j < 4; j++) {
				int nxx = xx + MiscUtils.DX4[j];
				int nyy = yy + MiscUtils.DY4[j];
				if (nxx >= 0 && nxx < map.length && nyy >= 0 && nyy < map[0].length && !map[nxx][nyy] && !visited[nxx][nyy]) {
					visited[nxx][nyy] = true;
					xQueue[size] = nxx;
					yQueue[size++] = nyy;
				}
			}
		}
		long answer = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!visited[i][j]) {
					long dx = ux[i + 1] - ux[i];
					long dy = uy[j + 1] - uy[j];
					answer += dx * dy;
				}
			}
		}
		out.printLine(answer);
	}
}
