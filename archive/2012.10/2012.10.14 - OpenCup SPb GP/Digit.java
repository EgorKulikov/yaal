package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Digit {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int a = in.readInt();
		int q = a;
		double w = a;
		int[] res1 = new int[10];
		int[] res2 = new int[10];
		for (int i = 0; i < n; i++) {
			res2[q]++;
			q = (q * a) % 10;
			res1[((int) Math.round(Math.floor(w)))]++;
			w = w * a;
			if (w >= 10) w *= 0.1;
		}
		for (int i = 1; i < 10; i++) {
			out.print(res1[i] + " ");
		}
    	out.printLine();
		for (int i = 0; i < 10; i++) {
			out.print(res2[i] + " ");
		}
		out.printLine();
	}
}
