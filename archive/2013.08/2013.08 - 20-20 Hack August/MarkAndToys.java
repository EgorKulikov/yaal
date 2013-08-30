package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MarkAndToys {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int money = in.readInt();
		int[] price = IOUtils.readIntArray(in, count);
		Arrays.sort(price);
		int answer = 0;
		for (int i : price) {
			money -= i;
			if (money < 0)
				break;
			answer++;
		}
		out.printLine(answer);
    }
}
