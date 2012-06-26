package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int basketCount = in.readInt();
		final int[] price = new int[count];
		final int[] type = new int[count];
		IOUtils.readIntArrays(in, price, type);
		Integer[] order = ArrayUtils.order(count, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (type[o1] != type[o2])
					return type[o1] - type[o2];
				return price[o2] - price[o1];
			}
		});
		int[][] answer = new int[basketCount][];
		for (int i = 0; i < basketCount - 1; i++)
			answer[i] = new int[]{order[i] + 1};
		answer[basketCount - 1] = new int[count - basketCount + 1];
		for (int i = 0; i < answer[basketCount - 1].length; i++)
			answer[basketCount - 1][i] = order[i + basketCount - 1] + 1;
		long score = 2 * ArrayUtils.sumArray(price);
		for (int[] basket : answer) {
			boolean hasChair = false;
			int minPrice = Integer.MAX_VALUE;
			for (int i : basket) {
				hasChair |= type[i - 1] == 1;
				minPrice = Math.min(minPrice, price[i - 1]);
			}
			if (hasChair)
				score -= minPrice;
		}
		out.printLine((score / 2) + "." + (score % 2 * 5));
		for (int[] basket : answer) {
			out.print(basket.length + " ");
			out.printLine(Array.wrap(basket).toArray());
		}
	}
}
