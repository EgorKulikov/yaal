package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int height = in.readInt();
		int width = in.readInt();
		if (height == 0 && width == 0)
			throw new UnknownError();
		int count = in.readInt();
		boolean[][] room = new boolean[height][width];
		for (int i = 0; i < count; i++) {
			int row = in.readInt();
			int column = in.readInt();
			room[row][column] = true;
		}
		while (true) {
			String direction = in.readString();
			if ("done".equals(direction))
				break;
			int distance = in.readInt();
			if ("left".equals(direction) || "right".equals(direction)) {
				int max = 0;
				for (int i = 0; i < height; i++) {
					int current = 0;
					for (int j = 0; j < width; j++) {
						if (room[i][j])
							current++;
					}
					max = Math.max(max, current);
				}
				distance = Math.min(distance, width - max);
			} else {
				int max = 0;
				for (int i = 0; i < width; i++) {
					int current = 0;
					for (int j = 0; j < height; j++) {
						if (room[j][i])
							current++;
					}
					max = Math.max(max, current);
				}
				distance = Math.min(distance, height - max);
			}
			if ("right".equals(direction)) {
				for (int i = 0; i < height; i++) {
					int current = 0;
					for (int j = 0; j < distance; j++) {
						if (room[i][j]) {
							room[i][j] = false;
							current++;
						}
					}
					for (int j = distance; current > 0; j++) {
						if (!room[i][j]) {
							room[i][j] = true;
							current--;
						}
					}
				}
			} else if ("left".equals(direction)) {
				for (int i = 0; i < height; i++) {
					int current = 0;
					for (int j = width - distance; j < width; j++) {
						if (room[i][j]) {
							room[i][j] = false;
							current++;
						}
					}
					for (int j = width - distance - 1; current > 0; j--) {
						if (!room[i][j]) {
							room[i][j] = true;
							current--;
						}
					}
				}
			} else if ("down".equals(direction)) {
				for (int i = 0; i < width; i++) {
					int current = 0;
					for (int j = 0; j < distance; j++) {
						if (room[j][i]) {
							room[j][i] = false;
							current++;
						}
					}
					for (int j = distance; current > 0; j++) {
						if (!room[j][i]) {
							room[j][i] = true;
							current--;
						}
					}
				}
			} else {
				for (int i = 0; i < width; i++) {
					int current = 0;
					for (int j = height - distance; j < height; j++) {
						if (room[j][i]) {
							room[j][i] = false;
							current++;
						}
					}
					for (int j = height - distance - 1; current > 0; j--) {
						if (!room[j][i]) {
							room[j][i] = true;
							current--;
						}
					}
				}
			}
		}
		out.print("Data set", testNumber, "ends with boxes at locations");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (room[i][j])
					out.print(" (" + i + "," + j + ")");
			}
		}
		out.printLine(".");
    }
}
