package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PetyaAndSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double eps = 1e-9;
		int count = in.readInt();
		final int[] array = IOUtils.readIntArray(in, count);
		if (ArrayUtils.sumArray(array) == 0) {
			out.printLine("YES");
			return;
		}
		if (count % 2 == 0) {
			double sum = 0;
			for (int i = 0; i < count; i++) {
				sum += (i % 2 == 1) ? array[i] : -array[i];
			}
			if (sum == 0) {
				out.printLine("YES");
				return;
			}
		}
		double[] re = new double[count];
		for (int i = 0; i < count; i++) {
			re[i] = 2 * Math.cos(2 * Math.PI * i / count);
		}
		for (int i = 1; 2 * i < count; i++) {
			double cur = array[0];
			double next = array[1];
			double coef = re[i];
			for (int j = 2; j < count; j++) {
				double nCur = next + coef * cur;
				next = array[j] - cur;
				cur = nCur;
			}
			if (Math.abs(cur) + Math.abs(next) < eps) {
				out.printLine("YES");
				return;
			}
		}
		out.printLine("NO");
    }
}
