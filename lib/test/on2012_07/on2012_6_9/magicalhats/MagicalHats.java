package on2012_07.on2012_6_9.magicalhats;



import net.egork.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicalHats {
	private int[] valid;
	private int[] result;
	private int[] current;
	private long[][] mask;
	private int[] add;

	public int findMaximumReward(String[] board, int[] coins, int numGuesses) {
		Arrays.sort(coins);
		boolean[][] arrayBoard = new boolean[board.length][board[0].length()];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length(); j++)
				arrayBoard[i][j] = board[i].charAt(j) == 'H';
		}
		int maxGet = go(arrayBoard, coins.length, numGuesses);
		if (maxGet == -1)
			return -1;
		int sum = 0;
		for (int i = 0; i < maxGet; i++)
			sum += coins[i];
		return sum;
	}

	private int go(boolean[][] board, int count, int numGuesses) {
		int hatCount = 0;
		for (boolean[] row : board) {
			for (boolean j : row) {
				if (j)
					hatCount++;
			}
		}
		int[] rowDegree = new int[board.length];
		int[] columnDegree = new int[board[0].length];
		int[] row = new int[hatCount];
		int[] column = new int[hatCount];
		int index = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j]) {
					row[index] = i;
					column[index++] = j;
					rowDegree[i]++;
					columnDegree[j]++;
				}
			}
		}
		List<Integer> valid = new ArrayList<Integer>();
		int[] curRowDegree = new int[rowDegree.length];
		int[] curColumnDegree = new int[columnDegree.length];
		for (int i = 0; i < (1 << hatCount); i++) {
			if (Integer.bitCount(i) == count) {
				Arrays.fill(curRowDegree, 0);
				Arrays.fill(curColumnDegree, 0);
				for (int j = 0; j < hatCount; j++) {
					if ((i >> j & 1) != 0) {
						curRowDegree[row[j]]++;
						curColumnDegree[column[j]]++;
					}
				}
				boolean good = true;
				for (int j = 0; j < rowDegree.length; j++) {
					if ((rowDegree[j] - curRowDegree[j]) % 2 != 0)
						good = false;
				}
				for (int j = 0; j < columnDegree.length; j++) {
					if ((columnDegree[j] - curColumnDegree[j]) % 2 != 0)
						good = false;
				}
				if (good)
					valid.add(i);
			}
		}
		if (valid.isEmpty())
			return -1;
		this.valid = new int[valid.size()];
		for (int i = 0; i < this.valid.length; i++)
			 this.valid[i] = valid.get(i);
		result = new int[(int) IntegerUtils.power(3, hatCount)];
		Arrays.fill(result, -1);
		current = new int[hatCount];
		mask = new long[hatCount][2];
		for (int i = 0; i < hatCount; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < this.valid.length; k++) {
					if ((this.valid[k] >> i & 1) == j)
						mask[i][j] += 1L << k;
				}
			}
		}
		add = new int[hatCount];
		add[0] = 1;
		for (int i = 1; i < hatCount; i++)
			add[i] = add[i - 1] * 3;
		return go(0, (1L << this.valid.length) - 1, numGuesses);
	}

	private int go(int mask, long validMask, int remainingGuesses) {
		if (result[mask] != -1)
			return result[mask];
		if (Long.bitCount(validMask) == 0)
			return result[mask] = 100500;
		if (remainingGuesses == 0)
			return result[mask] = 0;
		for (int i = 0; i < current.length; i++) {
			if (current[i] == 0) {
				int curResult = 100500;
				current[i] = 1;
				curResult = Math.min(curResult, go(mask + add[i], validMask & this.mask[i][0], remainingGuesses - 1));
				current[i] = 2;
				curResult = Math.min(curResult, 1 + go(mask + 2 * add[i], validMask & this.mask[i][1], remainingGuesses - 1));
				current[i] = 0;
				result[mask] = Math.max(result[mask], curResult);
			}
		}
		return result[mask];
	}


}

