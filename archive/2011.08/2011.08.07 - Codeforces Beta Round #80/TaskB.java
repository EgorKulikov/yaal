import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		if (vertexCount != edgeCount) {
			out.println("NO");
			return;
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(vertexCount);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(in.readInt() - 1, in.readInt() - 1);
		if (setSystem.getSetCount() == 1)
			out.println("FHTAGN!");
		else
			out.println("NO");
	}
}

