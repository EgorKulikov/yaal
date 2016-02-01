package on2015_12.on2015_12_08_December_Challenge_2015.Plane_Division;



import net.egork.collections.map.CPPMap;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlaneDivision {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Map<IntIntPair, Set<Rational>> counter = new CPPMap<>(HashSet::new);
		for (int i = 0; i < n; i++) {
			int a = in.readInt();
			int b = in.readInt();
			int c = in.readInt();
			int g = IntegerUtils.gcd(a, b);
			a /= g;
			b /= g;
			Rational cc = new Rational(c, g);
			if (a < 0) {
				a = -a;
				b = -b;
				cc = Rational.ZERO.subtract(cc);
			} else if (a == 0 && b < 0) {
				b = -b;
				cc = Rational.ZERO.subtract(cc);
			}
			counter.get(new IntIntPair(a, b)).add(cc);
		}
		out.printLine(counter.values().stream().mapToInt(Set::size).max().getAsInt());
	}
}
