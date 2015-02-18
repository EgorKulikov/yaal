package on2015_01.on2015_01_12_Codeforces_Round__285__Div__1_.A___Misha_and_Forest;



import net.egork.collections.intcollection.IntPair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] degree = new int[count];
		int[] xor = new int[count];
		IOUtils.readIntArrays(in, degree, xor);
		List<IntPair> answer = new ArrayList<>();
		int[] queue = new int[count];
		int size = 0;
		for (int i = 0; i < count; i++) {
			if (degree[i] == 1) {
				queue[size++] = i;
			}
		}
		for (int i = 0; i < size; i++) {
			int vertex = queue[i];
			if (degree[vertex] == 1) {
				int other = xor[vertex];
				answer.add(new IntPair(vertex, other));
				xor[other] ^= vertex;
				if (--degree[other] == 1) {
					queue[size++] = other;
				}
			}
		}
		out.printLine(answer.size());
		for (IntPair pair : answer) out.printLine(pair.first, pair.second);
    }
}
