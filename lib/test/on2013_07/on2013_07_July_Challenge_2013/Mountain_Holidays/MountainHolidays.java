package on2013_07.on2013_07_July_Challenge_2013.Mountain_Holidays;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MountainHolidays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		IntList delta = new IntArrayList(count - 1);
		for (int i = 1; i < count; i++)
			delta.add(heights[i] - heights[i - 1]);
		SuffixAutomaton automaton = new SuffixAutomaton(delta);
		long answer = 0;
		for (int i = 1; i < automaton.size; i++)
			answer += automaton.length[i] - automaton.length[automaton.link[i]];
		out.printLine(answer % 1000000009);
    }
}
