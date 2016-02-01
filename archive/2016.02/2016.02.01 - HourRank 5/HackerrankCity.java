package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class HackerrankCity {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = readIntArray(in, n);
		long nv = 1;
		long sd = 0;
		long dtc = 0;
		long diam = 0;
		for (int ne : a) {
			long nnv = nv * 4 + 2;
			long nsd = 4 * sd + 4 * dtc * (2 + 3 * nv % MOD) % MOD + 4 * ne * nv % MOD * (nv * 3 + 2) % MOD +
					ne * (2 * nv + 1) % MOD * (2 * nv + 1) % MOD;
			long ndtc = 4 * dtc + 8 * ne * nv % MOD + 3 * ne + (3 * nv + 2) * diam % MOD;
			long ndiam = 2 * diam + 3 * ne;
			nv = nnv % MOD;
			sd = nsd % MOD;
			dtc = ndtc % MOD;
			diam = ndiam % MOD;
		}
		out.printLine(sd);
	}
}
