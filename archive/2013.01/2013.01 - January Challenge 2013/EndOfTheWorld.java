package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class EndOfTheWorld {
	int[] primeIndex = new int[(int) 1e7];

	{
		boolean[] isPrime = IntegerUtils.generatePrimalityTable((int) 1e7);
		int j = 0;
		for (int i = 0; i < primeIndex.length; i++) {
			if (isPrime[i])
				primeIndex[i] = j++;
			else
				primeIndex[i] = -1;
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] numbers = IOUtils.readIntTable(in, size, size);
		boolean[][] processed = new boolean[size][size];
		int[] rowQueue = new int[size * size];
		int[] columnQueue = new int[size * size];
		long result = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (primeIndex[numbers[i][j]] != -1) {
					result += primeIndex[numbers[i][j]];
					continue;
				}
				if (processed[i][j])
					continue;
				int bit = numbers[i][j] & 1;
				if (bit == 0)
					result += numbers[i][j] >> 1;
				else
					result += (numbers[i][j] >> 1) + 2;
				rowQueue[0] = i;
				columnQueue[0] = j;
				processed[i][j] = true;
				int queueSize = 1;
				for (int k = 0; k < queueSize; k++) {
					int row = rowQueue[k];
					int column = columnQueue[k];
					for (int l = 0; l < 4; l++) {
						int newRow = row + MiscUtils.DX4[l];
						int newColumn = column + MiscUtils.DY4[l];
						if (newRow >= 0 && newRow < size && newColumn >= 0 && newColumn < size && !processed[newRow][newColumn] &&
							primeIndex[numbers[newRow][newColumn]] == -1 && bit == (numbers[newRow][newColumn] & 1))
						{
							rowQueue[queueSize] = newRow;
							columnQueue[queueSize++] = newColumn;
							processed[newRow][newColumn] = true;
						}
					}
				}
			}
		}
		out.printLine(result);
    }
}
