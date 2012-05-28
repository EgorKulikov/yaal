package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Cosmo {

	long[] workers;
	long[] factories;
	long[] soldiers;
	long[] buildSoldiers;
	long[] attackedBy;
	private int t;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		int k = in.readInt();
		t = in.readInt();
		workers = new long[t + 1];
		factories = new long[t + 1];
		soldiers = new long[t + 1];
		buildSoldiers = new long[t + 1];
		attackedBy = new long[t + 1];

		for (int i = 0; i < t - 1; i++) {
			attackedBy[i] = in.readLong();
		}

		workers[0] = n;
		factories[0] = k;
		for (int i = 0; i < t; i++) {
			if (!calcMove(i)) {
				out.printLine(-1);
				return;
			}
			if (soldiers[i + 1] < 0) {
				buildSoldiers[i] += -soldiers[i + 1];
				long canBuild = Math.min(factories[i], workers[i]);
				if (buildSoldiers[i] > canBuild) {
					if (i == 0) {
						out.printLine(-1);
						return;
					}
					buildSoldiers[i - 1] += (buildSoldiers[i] - canBuild);
					buildSoldiers[i] = canBuild;
					if (!calcMove(i - 1) || !calcMove(i) || soldiers[i + 1] < 0) {
						out.printLine(-1);
						return;
					}
				} else {
					if (!calcMove(i) || soldiers[i + 1] < 0) {
						out.printLine(-1);
						return;
					}
				}
			}
//			System.out.println(Arrays.toString(workers));
//			System.out.println(Arrays.toString(factories));
//			System.out.println(Arrays.toString(soldiers));
//			System.out.println(Arrays.toString(buildSoldiers));
//			System.out.println(Arrays.toString(attackedBy));
//			System.out.println();
		}
//		System.out.println(Arrays.toString(workers));
//		System.out.println(Arrays.toString(factories));
//		System.out.println(Arrays.toString(soldiers));
//		System.out.println(Arrays.toString(buildSoldiers));
//		System.out.println(Arrays.toString(attackedBy));
		out.printLine(soldiers[t]);
	}

	private boolean calcMove(int i) {
		long g = workers[i];
		long f = factories[i];
		if (i >= t - 2) {
			buildSoldiers[i] = Math.min(g, f);
		}
		if (buildSoldiers[i] > g || buildSoldiers[i] > f) {
			return false;
		}
		g -= buildSoldiers[i];
		f -= buildSoldiers[i];
		soldiers[i + 1] = soldiers[i] + buildSoldiers[i] - attackedBy[i];
		long bw = Math.min(g, f);
		workers[i + 1] = workers[i] + bw;
		g -= bw;
		factories[i + 1] = factories[i] + g;
		return true;
	}
}
