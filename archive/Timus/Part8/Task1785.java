package Timus.Part8;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1785 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		if (count <= 4)
			out.println("few");
		else if (count <= 9)
			out.println("several");
		else if (count <= 19)
			out.println("pack");
		else if (count <= 49)
			out.println("lots");
		else if (count <= 99)
			out.println("horde");
		else if (count <= 249)
			out.println("throng");
		else if (count <= 499)
			out.println("swarm");
		else if (count <= 999)
			out.println("zounds");
		else
			out.println("legion");
	}
}

