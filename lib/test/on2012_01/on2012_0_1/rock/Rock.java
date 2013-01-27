package on2012_01.on2012_0_1.rock;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.StringWrapper;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Rock {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Rational answer = Rational.ZERO;
		for (int i = 0; i < count; i++) {
			String next = in.readString();
			int pointCount = CollectionUtils.count(StringWrapper.wrap(next), '.');
			String[] tokens = next.replace(".", "").split("/");
			Rational current = new Rational(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])).multiply(
				new Rational((1 << (pointCount + 1)) - 1, 1 << pointCount));
			answer = answer.add(current);
		}
		out.printLine(answer.numerator, answer.denominator);
	}
}
