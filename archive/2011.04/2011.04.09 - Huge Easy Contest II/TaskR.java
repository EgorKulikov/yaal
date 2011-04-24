package April2011.UVaHugeEasyContestII;

import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.function.Function;
import net.egork.collections.sequence.ListWrapper;
import net.egork.collections.sequence.SequenceUtils;
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
		int drinkIndex = SequenceUtils.minIndex(ListWrapper.wrap(CollectionUtils.apply(ArrayWrapper.wrap(prices).
			subSequence(0, drinkCount), new Function<Integer, Long>()
		{
			public Long value(Integer argument) {
				return Math.abs((long) argument * itemCount - sumPrices) * 2 +
					((long) argument * itemCount - sumPrices > 0 ? 1 : 0);
			}
		})));
		int cakeIndex = SequenceUtils.minIndex(ListWrapper.wrap(CollectionUtils.apply(
			ArrayWrapper.wrap(prices).subSequence(drinkCount, drinkCount + cakeCount), new Function<Integer, Long>()
		{
			public Long value(Integer argument) {
				return Math.abs((long) argument * itemCount - sumPrices) * 2 +
					((long) argument * itemCount - sumPrices > 0 ? 1 : 0);
			}
		}))) + drinkCount;
		out.println("Case #" + testNumber + ": " + prices[drinkIndex] + " " + prices[cakeIndex]);
	}
}

