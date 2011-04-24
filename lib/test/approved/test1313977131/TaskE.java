package approved.test1313977131;

import net.egork.collections.map.CPPMap;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size, queryCount;
		try {
			size = in.readInt();
			queryCount = in.readInt();
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		int[] array = IOUtils.readIntArray(in, size);
		CPPMap<Integer, List<Integer>> map = new CPPMap<Integer, List<Integer>>(new Factory<List<Integer>>() {
			public List<Integer> create() {
				return new ArrayList<Integer>();
			}
		});
		for (int i = 0; i < array.length; i++)
			map.get(array[i]).add(i + 1);
		for (int it = 0; it < queryCount; it++) {
			int index = in.readInt() - 1;
			int value = in.readInt();
			if (map.containsKey(value) && map.get(value).size() > index)
				out.println(map.get(value).get(index));
			else
				out.println(0);
		}
	}
}

