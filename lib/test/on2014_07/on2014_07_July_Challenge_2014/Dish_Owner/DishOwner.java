package on2014_07.on2014_07_July_Challenge_2014.Dish_Owner;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DishOwner {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] score = IOUtils.readIntArray(in, count);
		IntList[] dishes = new IntList[count];
		int[] chef = ArrayUtils.createOrder(count);
		for (int i = 0; i < count; i++) {
			dishes[i] = new IntArrayList();
			dishes[i].add(i);
		}
		int[] at = ArrayUtils.createOrder(count);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 0) {
				int first = in.readInt() - 1;
				int second = in.readInt() - 1;
				if (at[first] == at[second]) {
					out.printLine("Invalid query!");
				} else {
					first = at[first];
					second = at[second];
					int firstValue = score[chef[first]];
					int secondValue = score[chef[second]];
					if (firstValue == secondValue) {
						continue;
					}
					if (dishes[first].size() < dishes[second].size()) {
						for (int j = 0; j < dishes[first].size(); j++) {
							int current = dishes[first].get(j);
							dishes[second].add(current);
							at[current] = second;
						}
						if (firstValue > secondValue) {
							chef[second] = chef[first];
						}
					} else {
						for (int j = 0; j < dishes[second].size(); j++) {
							int current = dishes[second].get(j);
							dishes[first].add(current);
							at[current] = first;
						}
						if (firstValue < secondValue) {
							chef[first] = chef[second];
						}
					}
				}
			} else {
				int index = in.readInt() - 1;
				out.printLine(chef[at[index]] + 1);
			}
		}
	}
}
