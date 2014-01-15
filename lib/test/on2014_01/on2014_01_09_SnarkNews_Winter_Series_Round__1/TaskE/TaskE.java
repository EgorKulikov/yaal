package on2014_01.on2014_01_09_SnarkNews_Winter_Series_Round__1.TaskE;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int publicKey = in.readInt();
		int encoded = in.readInt();
		int p = -1;
		int q = -1;
		for (int i = 2; ; i++) {
			if (number % i == 0) {
				p = i;
				q = number / i;
				break;
			}
		}
		int phi = (p - 1) * (q - 1);
		int privateKey = BigInteger.valueOf(publicKey).modInverse(BigInteger.valueOf(phi)).intValue();
		long answer = IntegerUtils.power(encoded, privateKey, number);
		out.printLine(answer);
    }
}
