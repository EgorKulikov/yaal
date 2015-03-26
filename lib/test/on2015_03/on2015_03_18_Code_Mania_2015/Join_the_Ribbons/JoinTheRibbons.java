package on2015_03.on2015_03_18_Code_Mania_2015.Join_the_Ribbons;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JoinTheRibbons {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long gcd = 0;
		for (int i = 0; i < count; i++) {
			gcd = IntegerUtils.gcd(gcd, in.readLong());
		}
		out.printLine(gcd);
	}
}
