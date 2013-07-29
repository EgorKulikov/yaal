package on2013_06.on2013_06_26_Yandex_Algorithm_Test_Round.Circles;



import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Circles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Counter<Pair<BigInteger, BigInteger>> counter = new Counter<Pair<BigInteger, BigInteger>>();
		for (int i = 0; i < count; i++) {
			long[] x = new long[3];
			long[] y = new long[3];
			IOUtils.readLongArrays(in, x, y);
			BigInteger sides = BigInteger.ONE;
			for (int j = 0; j < 3; j++) {
				long dx = x[(j + 1) % 3] - x[(j + 2) % 3];
				long dy = y[(j + 1) % 3] - y[(j + 2) % 3];
				sides = sides.multiply(BigInteger.valueOf(dx * dx + dy * dy));
			}
			long square = 0;
			for (int j = 0; j < 3; j++)
				square += (x[j] + x[(j + 1) % 3]) * (y[j] - y[(j + 1) % 3]);
			BigInteger bigSquare = BigInteger.valueOf(square).multiply(BigInteger.valueOf(square));
			BigInteger g = sides.gcd(bigSquare);
			sides = sides.divide(g);
			bigSquare = bigSquare.divide(g);
			counter.add(Pair.makePair(sides, bigSquare));
		}
		out.printLine(CollectionUtils.maxElement(counter.values()));
    }
}
