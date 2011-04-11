package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.arrays.ArrayUtils;
import net.egork.arrays.ArrayWrapper;
import net.egork.collections.Function;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskR implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		final int itemCount = in.readInt();
		int drinkCount = in.readInt();
		int cakeCount = in.readInt();
		int[] prices = in.readIntArray(itemCount);
		final long sumPrices = ArrayUtils.sumArray(prices);
		int drinkIndex = ArrayUtils.minIndex(ArrayWrapper.wrap(Function.apply(ArrayWrapper.wrap(prices).subArray(0, drinkCount), new Function<Integer, Long>() {
			@Override
			public Long value(Integer argument) {
				return Math.abs((long)argument * itemCount - sumPrices) * 2 + ((long)argument * itemCount - sumPrices > 0 ? 1 : 0);
			}
		})));
		int cakeIndex = ArrayUtils.minIndex(ArrayWrapper.wrap(Function.apply(ArrayWrapper.wrap(prices).subArray(drinkCount, drinkCount + cakeCount), new Function<Integer, Long>() {
			@Override
			public Long value(Integer argument) {
				return Math.abs((long)argument * itemCount - sumPrices) * 2 + ((long)argument * itemCount - sumPrices > 0 ? 1 : 0);
			}
		}))) + drinkCount;
		out.println("Case #" + testNumber + ": " + prices[drinkIndex] + " " + prices[cakeIndex]);
	}
}

