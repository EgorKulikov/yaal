package on2014_01.on2014_01_January_Challenge_2014.Bon_Appetit;



import net.egork.collections.map.CPPMap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.Factory;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class BonAppetit {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int[] start = new int[count];
		int[] finish = new int[count];
		int[] compartment = new int[count];
		IOUtils.readIntArrays(in, start, finish, compartment);
		MiscUtils.decreaseByOne(compartment);
		int[] order = ArrayUtils.order(finish);
		Map<Integer, Integer> until = new CPPMap<Integer, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		int answer = 0;
		for (int i : order) {
			if (until.get(compartment[i]) <= start[i]) {
				answer++;
				until.put(compartment[i], finish[i]);
			}
		}
		out.printLine(answer);
    }
}
