package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int bugCount = in.readInt();
		int maxCredits = in.readInt();
		int[] bugs = IOUtils.readIntArray(in, bugCount);
		int[] skill = IOUtils.readIntArray(in, count);
		int[] price = IOUtils.readIntArray(in, count);
		int[] bugOrder = ArrayUtils.order(bugs);
		int[] skillOrder = ArrayUtils.order(skill);
		int minDays = 1;
		int maxDays = bugCount + 1;
		while (minDays < maxDays) {
			int days = (minDays + maxDays) >> 1;
			if (solve(bugs, bugOrder, skill, price, skillOrder, maxCredits, days) == null)
				minDays = days + 1;
			else
				maxDays = days;
		}
		if (minDays == bugCount + 1)
			out.printLine("NO");
		else {
			out.printLine("YES");
			out.printLine(solve(bugs, bugOrder, skill, price, skillOrder, maxCredits, minDays));
		}
    }

	private int[] solve(int[] bugs, int[] bugOrder, int[] skill, final int[] price, int[] skillOrder, int maxCredits, int days) {
		int current = bugs.length - 1;
		int student = skillOrder.length - 1;
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return price[first] - price[second];
			}
		}, skill.length);
		int[] result = new int[bugs.length];
		while (current >= 0 && maxCredits >= 0) {
			while (student >= 0 && skill[skillOrder[student]] >= bugs[bugOrder[current]]) {
				heap.add(skillOrder[student--]);
			}
			if (heap.isEmpty())
				return null;
			int cheapest = heap.poll();
			maxCredits -= price[cheapest];
			current -= days;
			for (int i = Math.max(current + 1, 0); i <= current + days; i++)
				result[bugOrder[i]] = cheapest + 1;
		}
		if (maxCredits < 0)
			return null;
		return result;
	}
}
