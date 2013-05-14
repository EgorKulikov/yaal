package on2012_05.on2012_4_12.heavybooks;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;

import java.util.Arrays;
import java.util.Collections;

public class HeavyBooks {
	public int[] findWeight(int[] books, int[] moves) {
		int[] who = new int[moves[0]];
		Arrays.fill(who, 1);
		for (int i = 1; i < moves.length; i++) {
			int moveTo = i % 2 == 1 ? -1 : 1;
			int remainingMoves = moves[i];
			for (int j = 0; j < who.length && remainingMoves != 0; j++) {
				if (who[j] != moveTo) {
					who[j] = moveTo;
					remainingMoves--;
				}
			}
		}
		Collections.sort(Array.wrap(books), new ReverseComparator<Integer>());
		int[][] resultSum = new int[books.length + 1][who.length + 1];
		int[][] resultDifference = new int[books.length + 1][who.length + 1];
		ArrayUtils.fill(resultDifference, Integer.MIN_VALUE);
		resultDifference[0][0] = 0;
		resultSum[0][0] = 0;
		for (int i = 0; i < books.length; i++) {
			for (int j = 0; j <= i && j <= who.length; j++) {
				if (resultDifference[i + 1][j] < resultDifference[i][j] || resultDifference[i + 1][j] == resultDifference[i][j] && resultSum[i + 1][j] < resultSum[i][j]) {
					resultDifference[i + 1][j] = resultDifference[i][j];
					resultSum[i + 1][j] = resultSum[i][j];
				}
				if (j < who.length && (resultDifference[i + 1][j + 1] < resultDifference[i][j] + who[j] * books[i] || resultDifference[i + 1][j + 1] == resultDifference[i][j] + who[j] * books[i] && resultSum[i + 1][j + 1] < resultSum[i][j] + books[i])) {
					resultDifference[i + 1][j + 1] = resultDifference[i][j] + who[j] * books[i];
					resultSum[i + 1][j + 1] = resultSum[i][j] + books[i];
				}
			}
		}
		int tomek = (resultSum[books.length][who.length] - resultDifference[books.length][who.length]) / 2;
		int wojtek = resultSum[books.length][who.length] - tomek;
		return new int[]{tomek, wojtek};
	}

}

