package on2013_03.on2013_03_March_Challenge_2013.Fire_Escape_Routes;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FireEscapeRoutes {
	final static long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		IndependentSetSystem setSystem = new ListIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(in.readInt() - 1, in.readInt() - 1);
		int[] qty = new int[count];
		for (int i = 0; i < count; i++)
			qty[setSystem.get(i)]++;
		int answer = 0;
		long ways = 1;
		for (int i : qty) {
			if (i != 0) {
				answer++;
				ways *= i;
				ways %= MOD;
			}
		}
		out.printLine(answer, ways);
	}
}
