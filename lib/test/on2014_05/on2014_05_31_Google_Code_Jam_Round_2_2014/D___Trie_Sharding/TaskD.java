package on2014_05.on2014_05_31_Google_Code_Jam_Round_2_2014.D___Trie_Sharding;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);
	long[] factorial = IntegerUtils.generateFactorial(101, MOD);
	long[] reverseFactorial = IntegerUtils.generateReverseFactorials(101, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int serverCount = in.readInt();
		Node root = new Node();
		for (int i = 0; i < count; i++) {
			String s = in.readString();
			Node current = root;
			for (int j = 0; j < s.length(); j++) {
				current.qty++;
				int at = s.charAt(j) - 'A';
				if (current.children[at] == null)
					current.children[at] = new Node();
				current = current.children[at];
			}
			current.qty++;
			current.ends++;
		}
		int totalNodes = root.getTotalNodes(serverCount);
		long ways = root.getWays(serverCount);
		out.printLine("Case #" + testNumber + ":", totalNodes, ways);
    }

	class Node {
		int qty;
		int ends;
		Node[] children = new Node[26];

		public int getTotalNodes(int serverCount) {
			int result = Math.min(serverCount, qty);
			for (Node child : children) {
				if (child != null)
					result += child.getTotalNodes(serverCount);
			}
			return result;
		}

		public long getWays(int serverCount) {
			if (qty < serverCount)
				return factorial[serverCount] * reverseFactorial[serverCount - qty] % MOD;
			int maxChildrenQty = 0;
			for (Node child : children) {
				if (child != null)
					maxChildrenQty = Math.max(maxChildrenQty, child.qty);
			}
			maxChildrenQty = Math.min(maxChildrenQty, serverCount);
			long result = 0;
			long mult = 1;
			for (int i = serverCount; i >= maxChildrenQty; i--) {
				long current = IntegerUtils.power(i, ends, MOD);
				for (Node child : children) {
					if (child != null) {
						current *= child.getWays(i);
						current %= MOD;
					}
				}
				result += mult * current * c(serverCount, i) % MOD;
				mult *= -1;
			}
			result %= MOD;
			if (result < 0)
				result += MOD;
			return result;
		}
	}

	private long c(int n, int m) {
		return factorial[n] * reverseFactorial[m] % MOD * reverseFactorial[n - m] % MOD;
	}
}
