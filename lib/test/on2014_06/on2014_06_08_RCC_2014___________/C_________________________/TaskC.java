package on2014_06.on2014_06_08_RCC_2014___________.C_________________________;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskC {
	public static final int MAX = 100000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int coldCount = in.readInt();
		int hotCount = in.readInt();
		int coldTemperature = in.readInt();
		int hotTemperature = in.readInt();
		int queryCount = in.readInt();
		int[] coldVolume = IOUtils.readIntArray(in, coldCount);
		int[] hotVolume = IOUtils.readIntArray(in, hotCount);
		boolean[] coldSet = new boolean[MAX + 1];
		boolean[] hotSet = new boolean[MAX + 1];
		for (int i : coldVolume)
			coldSet[i] = true;
		for (int i : hotVolume)
			hotSet[i] = true;
		Map<Rational, Boolean> result = new HashMap<>();
		for (int i = 0; i < queryCount; i++) {
			int numerator = in.readInt();
			int denominator = in.readInt();
			long delta = numerator - (long)coldTemperature * denominator;
			if (delta <= 0) {
				out.printLine("NO");
				continue;
			}
			Rational hot = new Rational(delta, hotTemperature - coldTemperature);
			Rational cold = new Rational(denominator, 1).subtract(hot);
			if (cold.numerator <= 0) {
				out.printLine("NO");
				continue;
			}
			long lcm = IntegerUtils.lcm(hot.denominator, cold.denominator);
			long realHot = hot.numerator * lcm / hot.denominator;
			long realCold = cold.numerator * lcm / cold.denominator;
			long g = IntegerUtils.gcd(realHot, realCold);
			realHot /= g;
			realCold /= g;
			if (realHot > MAX || realCold > MAX) {
				out.printLine("NO");
				continue;
			}
			Rational ratio = new Rational(realHot, realCold);
			if (result.containsKey(ratio)) {
				out.printLine(result.get(ratio) ? "YES" : "NO");
				continue;
			}
			int max = (int) Math.min(MAX / realHot, MAX / realCold);
			boolean found = false;
			for (int j = 1; j <= max; j++) {
				if (hotSet[((int) (j * realHot))] && coldSet[((int) (j * realCold))]) {
					found = true;
					break;
				}
			}
			result.put(ratio, found);
			out.printLine(found ? "YES" : "NO");
		}
    }
}
