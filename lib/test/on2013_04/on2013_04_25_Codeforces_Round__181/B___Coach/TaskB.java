package on2013_04.on2013_04_25_Codeforces_Round__181.B___Coach;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(from[i], to[i]);
		IntList[] teams = new IntList[count];
		for (int i = 0; i < count; i++)
			teams[i] = new IntArrayList();
		for (int i = 0; i < count; i++)
			teams[setSystem.get(i)].add(i + 1);
		List<IntList> answer = new ArrayList<IntList>();
		List<IntList> pair = new ArrayList<IntList>();
		List<IntList> single = new ArrayList<IntList>();
		for (IntList team : teams) {
			if (team.size() > 3) {
				out.printLine(-1);
				return;
			}
			if (team.size() == 3)
				answer.add(team);
			if (team.size() == 2)
				pair.add(team);
			if (team.size() == 1)
				single.add(team);
		}
		if (pair.size() > single.size()) {
			out.printLine(-1);
			return;
		}
		for (int i = 0; i < pair.size(); i++) {
			IntList current = pair.get(i);
			current.addAll(single.get(i));
			answer.add(current);
		}
		for (int i = pair.size(); i < single.size(); i += 3) {
			IntList current = single.get(i);
			current.addAll(single.get(i + 1));
			current.addAll(single.get(i + 2));
			answer.add(current);
		}
		for (IntList team : answer)
			out.printLine(team);
    }
}
