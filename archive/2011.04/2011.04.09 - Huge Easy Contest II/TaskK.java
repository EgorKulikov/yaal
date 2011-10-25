package April2011.UVaHugeEasyContestII;

import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskK implements Solver {
	private static final char[] elements = {'A', 'C', 'G', 'T'};


	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int sequenceLength = in.readInt();
		int maxMutations = in.readInt();
		int initial = convert(in.readString());
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < (1 << (sequenceLength << 1)); i++) {
			int mask = 3;
			int different = 0;
			for (int j = 0; different <= maxMutations && j < sequenceLength; j++) {
				if ((initial & mask) != (i & mask))
					different++;
				mask <<= 2;
			}
			if (different <= maxMutations)
				result.add(convert(i, sequenceLength));
		}
		out.println(result.size());
		for (String dna : result)
			out.println(dna);
	}

	private String convert(int dna, int sequenceLength) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < sequenceLength; i++) {
			result.append(elements[dna & 3]);
			dna >>= 2;
		}
		return result.reverse().toString();
	}

	private int convert(String sequence) {
		long result = 0;
		for (char token : sequence.toCharArray()) {
			result <<= 2;
			if (token == 'C')
				result++;
			else if (token == 'G')
				result += 2;
			else if (token == 'T')
				result += 3;
		}
		return (int) result;
	}

}

