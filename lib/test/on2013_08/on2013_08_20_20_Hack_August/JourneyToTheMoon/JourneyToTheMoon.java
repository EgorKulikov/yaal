package on2013_08.on2013_08_20_20_Hack_August.JourneyToTheMoon;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JourneyToTheMoon {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(from[i], to[i]);
		int[] size = new int[count];
		for (int i = 0; i < count; i++)
			size[setSystem.get(i)]++;
		long answer = (long)count * (count - 1);
		for (int i : size)
			answer -= (long)i * (i - 1);
		answer >>= 1;
		out.printLine(answer);
    }
}
