package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	static final int BUBEN = 300;
	public static final int MAX = 100001;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long profit = in.readInt();
		int[] maxPrice = new int[count];
		int[] maxBanners = new int[count];
		IOUtils.readIntArrays(in, maxPrice, maxBanners);
		ArrayUtils.orderBy(maxBanners, maxPrice);
		int[] qty = new int[MAX];
		int[] baseQty = new int[(MAX + BUBEN) / BUBEN];
		int[] next = new int[MAX];
		long[] required = new long[MAX];
		int[] current = new int[(MAX + BUBEN) / BUBEN];
		Arrays.fill(required, Long.MAX_VALUE);
		for (int i = 0; i < current.length; i++)
			current[i] = Math.min(MAX - 1, (i + 1) * BUBEN - 1);
		int[] stack = new int[BUBEN];
		long[] base = new long[BUBEN];
		long[] switchAt = new long[BUBEN];
		int curCustomer = 0;
		for (int i = 0; i < maxBanners[count - 1] + 2; i++) {
			while (curCustomer < count && i > maxBanners[curCustomer]) {
				for (int j = 0; (j + 1) * BUBEN <= maxPrice[curCustomer]; j++) {
					baseQty[j]++;
					if (baseQty[j] == required[current[j]]) {
						current[j] = next[current[j]];
					}
				}
				int toUpdate = maxPrice[curCustomer] / BUBEN;
				for (int j = toUpdate * BUBEN; j < (toUpdate + 1) * BUBEN && j < MAX; j++) {
					qty[j] += baseQty[toUpdate];
				}
				baseQty[toUpdate] = 0;
				for (int j = toUpdate * BUBEN; j <= maxPrice[curCustomer]; j++)
					qty[j]++;
				int stackSize = 0;
				for (int j = toUpdate * BUBEN; j < (toUpdate + 1) * BUBEN && j < MAX; j++) {
					long curBase = (long) j * qty[j];
					if (stackSize == 0) {
						stack[stackSize] = j;
						base[stackSize] = curBase;
						switchAt[stackSize++] = 0;
						continue;
					}
					while (stackSize > 0) {
						long lastBase = base[stackSize - 1];
						long c = stack[stackSize - 1];
						long deltaBase = lastBase - curBase;
						if (deltaBase < 0) {
							stackSize--;
							continue;
						}
						long curSwitch = (deltaBase + j - c - 1) / (j - c);
						if (curSwitch <= switchAt[stackSize - 1]) {
							stackSize--;
						} else {
							switchAt[stackSize] = curSwitch;
							break;
						}
					}
					stack[stackSize] = j;
					base[stackSize++] = curBase;
				}
				current[toUpdate] = stack[0];
				for (int j = 0; j < stackSize - 1; j++) {
					next[stack[j]] = stack[j + 1];
					required[stack[j]] = switchAt[j + 1];
				}
				required[stack[stackSize - 1]] = Long.MAX_VALUE;
				curCustomer++;
			}
			long maxProfit = 0;
			int pricePoint = 0;
			for (int j = 0; j < current.length; j++) {
				long candidate = (long)current[j] * (baseQty[j] + qty[current[j]]);
				if (candidate > maxProfit) {
					maxProfit = candidate;
					pricePoint = current[j];
				}
			}
			maxProfit += i * profit * (count - curCustomer);
			out.printLine(maxProfit, pricePoint);
		}
	}
}
