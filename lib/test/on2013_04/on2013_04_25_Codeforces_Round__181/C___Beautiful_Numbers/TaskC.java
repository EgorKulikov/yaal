package on2013_04.on2013_04_25_Codeforces_Round__181.C___Beautiful_Numbers;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int count = in.readInt();
		IntList good = new IntArrayList();
		good.add(first);
		good.add(second);
		for (int i = 0; i < good.size(); i++) {
			int current = good.get(i);
			if (current * 10 + first <= count * second)
				good.add(current * 10 + first);
			if (current * 10 + second <= count * second)
				good.add(current * 10 + second);
		}
		long answer = 0;
		for (int i : good.toArray()) {
			if (i < first * count || (i - first * count) % (second - first) != 0)
				continue;
			answer += IntegerUtils.binomialCoefficient(count, (i - first * count) / (second - first), MOD);
		}
		answer %= MOD;
		out.printLine(answer);
    }
}
