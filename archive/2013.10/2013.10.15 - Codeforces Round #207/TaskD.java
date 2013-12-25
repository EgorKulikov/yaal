package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int money = in.readInt();
		int[] purses = IOUtils.readIntArray(in, count);
		money -= ArrayUtils.maxElement(purses);
		if (money < 0) {
			out.printLine(-1);
			return;
		}
		int skip = ArrayUtils.maxPosition(purses);
		int[] can = new int[(money + 32) >> 5];
		int[][] snapshots = new int[(count + 7) >> 3][];
		can[0] = 1;
		for (int i = 0; i < count; i++) {
			if ((i & 7) == 0)
				snapshots[i >> 3] = can.clone();
			if (i == skip)
				continue;
			int ind1 = purses[i] >> 5;
			int sh1 = (purses[i] & 31);
			int mask1;
			if (sh1 == 0)
				mask1 = -1;
			else
				mask1 = (1 << (32 - sh1)) - 1;
			int ind2 = ind1 + 1;
			int sh2 = 32 - sh1;
			int mask2 = (1 << sh1) - 1;
			if (ind1 < can.length)
				can[can.length - 1] |= (can[can.length - 1 - ind1] & mask1) << sh1;
			for (int j = can.length - 2 - ind1; j >= 0; j--) {
				can[j + ind2] |= (can[j] >>> sh2) & mask2;
				can[j + ind1] |= (can[j] & mask1) << sh1;
			}
		}
		boolean[] outer = new boolean[count];
		int at = count;
		int value = money;
		if (((can[value >> 5] >>> (value & 31)) & 1) == 0) {
			out.printLine(-1);
			return;
		}
		for (int i = snapshots.length - 1; i >= 0; i--) {
			int length = at - i * 8;
			for (int j = 0; j < (1 << length); j++) {
				if (skip >= i * 8 && skip < at && ((j >> (skip - i * 8) & 1) == 1))
					continue;
				int sum = 0;
				for (int k = 0; k < length; k++) {
					if ((j >> k & 1) == 1)
						sum += purses[i * 8 + k];
				}
				if (sum <= value) {
					int remaining = value - sum;
					if (((snapshots[i][remaining >> 5] >>> (remaining & 31)) & 1) == 1) {
						for (int k = 0; k < length; k++) {
							if ((j >> k & 1) == 1)
								outer[i * 8 + k] = true;
						}
						value -= sum;
						break;
					}
				}
			}
			at = i * 8;
		}
		int[] order = ArrayUtils.order(purses);
		int[] son = new int[count];
		Arrays.fill(son, -1);
		int last = -1;
		for (int i : order) {
			if (outer[i])
				continue;
			son[i] = last;
			last = i;
		}
		for (int i = 0; i < count; i++) {
			if (son[i] == -1)
				out.printLine(purses[i], 0);
			else
				out.printLine(purses[i] - purses[son[i]], 1, son[i] + 1);
		}
    }
}
