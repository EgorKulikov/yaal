package on2012_09.on2012_09_10_Russian_CodeCup_Finals.D________;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int budget = in.readInt();
		int enemyBudget = in.readInt();
		int count = in.readInt();
		int[] buildCost = new int[count];
		int[] destroyCost = new int[count];
		IOUtils.readIntArrays(in, buildCost, destroyCost);
		int[] sum = new int[budget + 1];
		int[] good = new int[budget + 1];
		short[][] last = new short[count][budget + 1];
		int[] order = ArrayUtils.order(destroyCost);
		Arrays.fill(last[0], (short) -1);
		for (int i1 = 0, orderLength = order.length; i1 < orderLength; i1++) {
			int i = order[i1];
			int curDestroyCost = destroyCost[i];
			int index = budget;
			if (i1 != 0)
				System.arraycopy(last[i1 - 1], 0, last[i1], 0, budget + 1);
			for (int j = budget - buildCost[i]; j >= 0; j--) {
				int newGood = 0;
				int newCost = 0;
				if (good[j] != 0)
					newGood = good[j] + 1;
				else {
					newCost = sum[j] + curDestroyCost;
					if (newCost > enemyBudget)
						newGood = 1;
				}
				if (newGood > good[index] || good[index] == 0 && newCost > sum[index]) {
					good[index] = newGood;
					sum[index] = newCost;
					last[i1][index] = (short) i;
				}
				index--;
			}
		}
		int bestAt = 0;
		int lastIndex = count - 1;
		for (int i = 1; i <= budget; i++) {
			if (good[i] > good[bestAt])
				bestAt = i;
		}
//		System.err.println(good[budget] + " " + sum[budget]);
		int[] reverse = new int[count];
		for (int i = 0; i < count; i++)
			reverse[order[i]] = i;
		out.printLine(good[bestAt]);
		List<Integer> willBuild = new ArrayList<Integer>();
		while (bestAt != 0) {
			willBuild.add(last[lastIndex][bestAt] + 1);
			int nextLastIndex = reverse[last[lastIndex][bestAt]] - 1;
			bestAt -= buildCost[last[lastIndex][bestAt]];
			lastIndex = nextLastIndex;
		}
		Collections.sort(willBuild);
		out.print(willBuild.size() + " ");
		out.printLine(willBuild.toArray());
	}
}
