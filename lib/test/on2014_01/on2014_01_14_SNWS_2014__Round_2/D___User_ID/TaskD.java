package on2014_01.on2014_01_14_SNWS_2014__Round_2.D___User_ID;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		List<Pair<Long,Integer>> list = IntegerUtils.factorize(number);
		IntList answer = new IntArrayList();
		for (Pair<Long, Integer> pair : list) {
			for (int i = 0; i < 30; i++) {
				if ((pair.second >> i & 1) != 0)
					answer.add((int) IntegerUtils.power(pair.first, 1 << i));
			}
		}
		answer.inPlaceSort();
		out.printLine(answer);
	}
}
