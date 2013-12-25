package on2012_08.on2012_7_12.frequentterms;



import net.egork.collections.Pair;
import net.egork.collections.map.Counter;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrequentTerms {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Counter<String> counter = new Counter<String>();
		for (int i = 0; i < count; i++)
			counter.add(in.readString());
		int printFirstCount = in.readInt();
		List<Pair<Long, String>> answer = new ArrayList<Pair<Long, String>>();
		for (String term : counter.keySet())
			answer.add(Pair.makePair(-counter.get(term), term));
		Collections.sort(answer);
		for (Pair<Long, String> pair : answer.subList(0, printFirstCount)) {
			out.printLine(pair.second);
		}
	}
}
