package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] periods = IOUtils.readIntArray(in, count);
		Rational minC = new Rational(9, 10);
		Rational maxC = new Rational(11, 10);
		Rational minT = minC.multiply(periods[0]);
		Rational maxT = maxC.multiply(periods[0]);
		StringBuilder result = new StringBuilder();
		result.append(1);
		int last = 1;
		for (int i = 1; i < periods.length - 1; i++) {
			Rational curMinT = minC.multiply(periods[i]);
			Rational curMaxT = maxC.multiply(periods[i]);
			boolean change = false;
			if (curMinT.compareTo(maxT) > 0) {
				curMinT = curMinT.divide(2);
				curMaxT = curMaxT.divide(2);
				change = true;
			}
			if (curMaxT.compareTo(minT) < 0 || curMinT.compareTo(maxT) > 0) {
				out.printLine("ERROR");
				return;
			}
			minT = MiscUtils.max(curMinT, minT);
			maxT = MiscUtils.min(curMaxT, maxT);
			if (change)
				last = 1 - last;
			else {
				i++;
				if (i == count - 1) {
					out.printLine("ERROR");
					return;
				}
				curMinT = minC.multiply(periods[i]);
				curMaxT = maxC.multiply(periods[i]);
				if (curMaxT.compareTo(minT) < 0 || curMinT.compareTo(maxT) > 0) {
					out.printLine("ERROR");
					return;
				}
				minT = MiscUtils.max(curMinT, minT);
				maxT = MiscUtils.min(curMaxT, maxT);
			}
			result.append(last);
		}
		Rational curMinT = minC.multiply(periods[count - 1]);
		Rational curMaxT = maxC.multiply(periods[count - 1]);
		if (curMaxT.compareTo(minT) < 0 || curMinT.compareTo(maxT) > 0 || last == 1) {
			out.printLine("ERROR");
			return;
		}
		out.printLine(result);
	}
}
