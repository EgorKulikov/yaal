package net.egork;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BitPermutation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int a = in.readInt();
		int c = in.readInt();
		int s = in.readInt();
		int[] p = new int[32];
		for (int i = 0; i < 32; i++) {
			p[i] = in.readInt();
		}
		long[] ss = new long[32];
		for (int i = 0; i < 32; i++) {
			long z = 1L << (i + 1);
			int nn = (int) (n % z);
			int zz = (int) (n / z);
			ss[i] = (z / 2) * zz;
			int cs = s;
			for (int j = 0; j < nn; j++) {
				cs = cs * a + c;
				ss[i] += (cs >> i) & 1;
			}
		}
		long res = 0;
		for (int i = 0; i < 32; i++) {
			res += (1 << p[i]) * ss[i];
		}
		out.printLine(res);
    }
}
