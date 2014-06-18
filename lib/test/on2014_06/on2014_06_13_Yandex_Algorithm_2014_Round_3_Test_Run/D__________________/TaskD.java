package on2014_06.on2014_06_13_Yandex_Algorithm_2014_Round_3_Test_Run.D__________________;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String from = in.readString();
		long delta = in.readInt();
		if (from.charAt(0) == '-') {
			if (delta < 0) {
				out.printLine(-1);
				return;
			}
			from = "0";
			delta--;
		}
		BigInteger value = new BigInteger(from);
		BigInteger palindrom = new BigInteger(from.substring(0, (from.length() + 1) / 2) + StringUtils.reverse(from.substring(0, from.length() / 2)));
		if (value.compareTo(palindrom) > 0) {
			if (delta < 0)
				delta++;
		} else if (value.compareTo(palindrom) < 0) {
			if (delta > 0)
				delta--;
		}
		BigInteger start = new BigInteger(from.substring(0, (from.length() + 1) / 2));
		BigInteger target = start.add(BigInteger.valueOf(delta));
		String targetString = target.toString();
		if (targetString.length() == start.toString().length() && target.compareTo(BigInteger.ZERO) >= 0) {
			out.printLine(targetString + StringUtils.reverse(targetString.substring(0, from.length() / 2)));
			return;
		}
		if (delta > 0) {
			BigInteger difference = BigInteger.TEN.pow((from.length() - 1) / 2 + 1).subtract(start);
			delta -= difference.intValue();
			int curLength = from.length() + 1;
			while (true) {
				BigInteger nextLength = BigInteger.TEN.pow((curLength - 1) / 2).multiply(BigInteger.valueOf(9));
				if (nextLength.compareTo(BigInteger.valueOf(delta)) > 0) {
					targetString = BigInteger.TEN.pow((curLength - 1) / 2).add(BigInteger.valueOf(delta)).toString();
					out.printLine(targetString + StringUtils.reverse(targetString.substring(0, curLength / 2)));
					return;
				}
				delta -= nextLength.longValue();
				curLength++;
			}
		} else {
			BigInteger difference = start.subtract(BigInteger.TEN.pow((from.length() - 1) / 2));
			delta = -delta;
			delta -= difference.intValue();
			int curLength = from.length() - 1;
			while (curLength > 0) {
				BigInteger nextLength = BigInteger.TEN.pow((curLength - 1) / 2).multiply(BigInteger.valueOf(9));
				if (nextLength.compareTo(BigInteger.valueOf(delta)) >= 0) {
					targetString = BigInteger.TEN.pow((curLength - 1) / 2 + 1).subtract(BigInteger.valueOf(delta)).toString();
					out.printLine(targetString + StringUtils.reverse(targetString.substring(0, curLength / 2)));
					return;
				}
				delta -= nextLength.longValue();
				curLength--;
			}
			if (delta == 1)
				out.printLine(0);
			else
				out.printLine(-1);
		}
    }
}
