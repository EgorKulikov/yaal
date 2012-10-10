package on2011_12.on2011_11_14.taskb;



import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.Stack;
import java.util.TreeSet;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int setCount = in.readInt();
		if (count == 0 && setCount == 0)
			throw new UnknownError();
		if (testNumber != 1)
			out.printLine();
		Stack<int[]> sets = new Stack<int[]>();
		for (int i = 0; i < setCount; i++) {
			String line = in.readLine();
			String[] tokens = line.split(" ");
			int[] array = new int[tokens.length];
			for (int j = 0; j < array.length; j++)
				array[j] = Integer.parseInt(tokens[j]);
			sets.add(array);
		}
		NavigableSet<Integer> unused = new TreeSet<Integer>();
		while (!sets.isEmpty()) {
			int[] array = sets.pop();
			if (array.length <= 2 && unused.isEmpty() || array.length == 2 && array[1] > unused.last()) {
				for (int i : array)
					unused.add(i);
				continue;
			}
			if (unused.isEmpty() || array[array.length - 1] > unused.last()) {
				unused.add(array[array.length - 1]);
				array = Arrays.copyOf(array, array.length - 1);
				int preLast = array[array.length - 1];
				array[array.length - 1] = unused.tailSet(preLast, true).pollFirst();
				unused.add(preLast);
				sets.add(array);
				break;
			}
			array = Arrays.copyOf(array, array.length + 1);
			array[array.length - 1] = unused.tailSet(array[array.length - 2], false).pollFirst();
			sets.add(array);
			break;
		}
		for (int i : unused)
			sets.add(new int[]{i});
		out.printLine(count, sets.size());
		for (int[] array : sets)
			out.printLine(Array.wrap(array).toArray());
	}
}
