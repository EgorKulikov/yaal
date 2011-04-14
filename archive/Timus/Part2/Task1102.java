package Timus.Part2;

import net.egork.string.PrefixTree;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1102 implements Solver {
	private PrefixTree tree;
	private byte[] input = new byte[10000000];

	{
		tree = new PrefixTree();
		tree.add("tuo");
		tree.add("tuptuo");
		tree.add("notup");
		tree.add("ni");
		tree.add("tupni");
		tree.add("eno");
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int index = 0;
		while (true) {
			input[index] = (byte) in.read();
			if (input[index] == '\n' || input[index] == '\r' || input[index] == -1) {
				if (index == 0)
					continue;
				break;
			}
			index++;
		}
		PrefixTree.Visitor visitor = tree.getVisitor();
		//noinspection StatementWithEmptyBody
		for (int i = index - 1; i >= 0 && visitor.accept((char) input[i]); i--);
		out.println(visitor.isAtRoot() ? "YES" : "NO");
	}
}

