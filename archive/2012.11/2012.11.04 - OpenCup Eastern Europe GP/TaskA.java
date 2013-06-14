package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int money = in.readInt();
		int[] prices = IOUtils.readIntArray(in, count);
		int[] quantity = IOUtils.readIntArray(in, count);
		int maxPrice = 0;
		for (int x : prices)
			maxPrice = Math.max(maxPrice, x);
		int maxQuantity = 0;
		for (int x : quantity)
			maxQuantity = Math.max(maxQuantity, x);
		money = Math.min(money, maxQuantity * maxPrice);
		int[][] best = new int[maxQuantity + 1][money + 1];
		int[][] nbest = new int[maxQuantity + 1][money + 1];
		ArrayUtils.fill(best, -1);
		best[0][0] = 0;
		int[] tmpArr = new int[maxQuantity + 1];
		int[] queue = new int[maxQuantity + 1];
		for (int pos = count - 1; pos >= 0; --pos) {
			ArrayUtils.fill(nbest, -1);
			for (int moneyToTop = money + maxQuantity * maxPrice; moneyToTop >= 0; --moneyToTop) {
				for (int q = 0; q <= maxQuantity; ++q) {
					int moneyAt = moneyToTop - (maxQuantity - q) * prices[pos];
					if (moneyAt < 0 || moneyAt > money) {
						tmpArr[q] = -1;
					} else {
						tmpArr[q] = best[q][moneyAt];
					}
				}
				int qTail = 0;
				int qHead = 0;
				int cq = quantity[pos];
				for (int q = 0; q <= maxQuantity; ++q) {
					while (qHead > qTail && tmpArr[queue[qHead - 1]] <= tmpArr[q]) {
						--qHead;
					}
					queue[qHead++] = q;
					if (queue[qTail] + cq < q)
						++qTail;
					int moneyAt = moneyToTop - (maxQuantity - q) * prices[pos];
					int cMax = tmpArr[queue[qTail]];
					if (moneyAt >= 0 && moneyAt <= money && cMax >= 0) {
						nbest[q][moneyAt] = Math.max(nbest[q][moneyAt], cMax + Math.min(cq, q) * prices[pos]);
					}
				}
			}
			int[][] tmp = best;
			best = nbest;
			nbest = tmp;
		}
		int res = -1;
		for (int[] x : best) for (int y : x) res = Math.max(res, y);
		out.printLine(res);
	}
}
