package on2012_07.on2012_6_20.task1590;



import net.egork.string.SuffixTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1590 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        SuffixTree tree = new SuffixTree(s, 'a', 'z');
        int result = go(tree.root);
        out.printLine(result);
	}

    private int go(SuffixTree.Node root) {
        int result = root.to - root.from;
        for (SuffixTree.Node node : root.children) {
            if (node != null)
                result += go(node);
        }
        return result;
    }
}
