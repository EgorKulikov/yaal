package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndNotebooks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int pagesNeeded = in.readInt();
		pagesNeeded -= in.readInt();
		int money = in.readInt();
		int count = in.readInt();
		int[] pages = new int[count];
		int[] cost = new int[count];
		IOUtils.readIntArrays(in, pages, cost);
		for (int i = 0; i < count; i++) {
			if (pagesNeeded <= pages[i] && money >= cost[i]) {
				out.printLine("LuckyChef");
				return;
			}
		}
		out.printLine("UnluckyChef");
	}
}
