package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ATestOfSeries {
	List<BigInteger> f = new ArrayList<BigInteger>();

	{
		f.add(BigInteger.ONE);
		f.add(BigInteger.ONE);
		while (f.get(f.size() - 1).toString().length() <= 1000)
			f.add(f.get(f.size() - 1).add(f.get(f.size() - 2)));
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt() - 1;
		if (index == -1)
			throw new UnknownError();
		out.printLine(f.get(index));
    }
}
