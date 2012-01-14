package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Mihnevsev {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int column = in.readInt() - 1;
		int row = in.readInt() - 1;
		int[][] intensity = IOUtils.readIntTable(in, size, size);
		while (true) {
			out.printLine(column + 1, row + 1);
			int direction = -1;
			int maxIntensity = intensity[row][column];
			for (int i = 0; i < 4; i++) {
				int newRow = row + MiscUtils.DX4[i];
				int newColumn = column + MiscUtils.DY4[i];
				if (newRow >= 0 && newRow < size && newColumn >= 0 && newColumn < size &&
					intensity[newRow][newColumn] > maxIntensity)
				{
					maxIntensity = intensity[newRow][newColumn];
					direction = i;
				}
			}
			if (direction == -1)
				return;
			row += MiscUtils.DX4[direction];
			column += MiscUtils.DY4[direction];
		}
	}
}
