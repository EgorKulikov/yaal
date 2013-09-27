package on2013_09.on2013_09_September_Challenge_2013.CAO_Stage_2;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CAOStage {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int[][] length = new int[rowCount][columnCount];
		ArrayUtils.fill(length, Integer.MAX_VALUE);
		for (int i = 0; i < rowCount; i++) {
			int current = -1;
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == '#')
					current = -1;
				else
					current++;
				length[i][j] = Math.min(length[i][j], current);
			}
		}
		for (int i = 0; i < rowCount; i++) {
			int current = -1;
			for (int j = columnCount - 1; j >= 0; j--) {
				if (map[i][j] == '#')
					current = -1;
				else
					current++;
				length[i][j] = Math.min(length[i][j], current);
			}
		}
		for (int j = 0; j < columnCount; j++) {
			int current = -1;
			for (int i = 0; i < rowCount; i++) {
				if (map[i][j] == '#')
					current = -1;
				else
					current++;
				length[i][j] = Math.min(length[i][j], current);
			}
		}
		for (int j = 0; j < columnCount; j++) {
			int current = -1;
			for (int i = rowCount - 1; i >= 0; i--) {
				if (map[i][j] == '#')
					current = -1;
				else
					current++;
				length[i][j] = Math.min(length[i][j], current);
			}
		}
		int[] primes = IntegerUtils.generatePrimes(rowCount);
		int answer = 0;
		for (int[] row : length) {
			for (int i : row) {
				int current = Arrays.binarySearch(primes, i);
				if (current >= 0)
					current++;
				else
					current = -current - 1;
				answer += current;
			}
		}
		out.printLine(answer);
    }
}
