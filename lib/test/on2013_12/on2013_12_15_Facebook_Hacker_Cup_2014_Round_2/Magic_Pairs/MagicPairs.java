package on2013_12.on2013_12_15_Facebook_Hacker_Cup_2014_Round_2.Magic_Pairs;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.set.EHashSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class MagicPairs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int firstCount = in.readInt();
		int secondCount = in.readInt();
		int[] fColors = new int[firstCount];
		int[] sColors = new int[secondCount];
		fColors[0] = in.readInt();
		long a1 = in.readInt();
		long b1 = in.readInt();
		int c1 = in.readInt();
		int r1 = in.readInt();
		sColors[0] = in.readInt();
		long a2 = in.readInt();
		long b2 = in.readInt();
		int c2 = in.readInt();
		int r2 = in.readInt();
		for (int i = 1; i < Math.min(firstCount, secondCount); i++) {
			fColors[i] = (int) ((a1 * fColors[i - 1] + b1 * sColors[i - 1] + c1) % r1);
			sColors[i] = (int) ((a2 * fColors[i - 1] + b2 * sColors[i - 1] + c2) % r2);
		}
		for (int i = Math.min(firstCount, secondCount); i < firstCount; i++)
			fColors[i] = (int) ((a1 * fColors[i - 1] + b1 * sColors[(i - 1) % secondCount] + c1) % r1);
		for (int i = Math.min(firstCount, secondCount); i < secondCount; i++)
			sColors[i] = (int) ((a2 * fColors[(i - 1) % firstCount] + b2 * sColors[i - 1] + c2) % r2);
		IntList firstOrder = new IntArrayList();
		IntList firstLength = new IntArrayList();
		buildOrder(firstOrder, firstLength, fColors);
		IntList secondOrder = new IntArrayList();
		IntList secondLength = new IntArrayList();
		buildOrder(secondOrder, secondLength, sColors);
		Set<Integer> firstNotSecond = new EHashSet<Integer>();
		Set<Integer> secondNotFirst = new EHashSet<Integer>();
		long answer = 0;
		for (int i = 0; i < Math.min(firstOrder.size(), secondOrder.size()); i++) {
			int curFirst = firstOrder.get(i);
			int curSecond = secondOrder.get(i);
			if (curFirst != curSecond) {
				if (secondNotFirst.contains(curFirst))
					secondNotFirst.remove(curFirst);
				else
					firstNotSecond.add(curFirst);
				if (firstNotSecond.contains(curSecond))
					firstNotSecond.remove(curSecond);
				else
					secondNotFirst.add(curSecond);
			}
			if (firstNotSecond.isEmpty())
				answer += (long) firstLength.get(i) * secondLength.get(i);
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }

	private void buildOrder(IntList order, IntList length, int[] colors) {
		IntSet present = new IntHashSet();
		for (int i : colors) {
			if (present.contains(i))
				length.set(length.size() - 1, length.back() + 1);
			else {
				present.add(i);
				order.add(i);
				length.add(1);
			}
		}
	}
}
