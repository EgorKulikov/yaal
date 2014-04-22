package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double[] naomi = IOUtils.readDoubleArray(in, count);
		double[] ken = IOUtils.readDoubleArray(in, count);
		Arrays.sort(naomi);
		Arrays.sort(ken);
		int atKen = 0;
		int deceitfulWar = 0;
		for (int i = 0; i < count; i++) {
			if (naomi[i] > ken[atKen]) {
				deceitfulWar++;
				atKen++;
			}
		}
		int atNaomi = 0;
		int war = count;
		for (int i = 0; i < count; i++) {
			if (ken[i] > naomi[atNaomi]) {
				war--;
				atNaomi++;
			}
		}
		out.printLine("Case #" + testNumber + ":", deceitfulWar, war);
    }
}
