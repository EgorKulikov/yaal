import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskI implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		IndependentSetSystem system = new ListIndependentSetSystem(vertexCount);
		for (int i = 0; i < edgeCount; i++)
			system.join(in.readInt() - 1, in.readInt() - 1);
		out.println(system.getSetCount());
	}
}

