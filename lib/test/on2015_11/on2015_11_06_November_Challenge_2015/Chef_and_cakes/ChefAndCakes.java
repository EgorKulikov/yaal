package on2015_11.on2015_11_06_November_Challenge_2015.Chef_and_cakes;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndCakes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int withCake = n / IntegerUtils.gcd(n, m);
		if (withCake == n) {
			out.printLine("Yes");
		} else {
			out.printLine("No", withCake);
		}
	}
}
