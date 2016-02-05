package on2015_12.on2015_12_03_NEERC_2015_Practice_Session.IP;



import net.egork.generated.collections.function.IntIntToIntFunction;
import net.egork.generated.collections.list.IntArray;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class IP {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int m = in.readInt();
		int[] ips = new int[m];
		for (int i = 0; i < m; i++) {
			ips[i] = MiscUtils.parseIP(in.readString());
		}
		int or = new IntArray(ips).reduce((a, b) -> a | b);
		int and = new IntArray(ips).reduce(-1, (IntIntToIntFunction)(a, b) -> a & b);
		int mask = -1 - (Integer.highestOneBit(or ^ and) * 2 - 1);
		if (or == and)
			mask = -1;
		int address = or & mask;
		out.printLine(MiscUtils.buildIP(address));
		out.printLine(MiscUtils.buildIP(mask));
	}
}
