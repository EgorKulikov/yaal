package on2012_05.on2012_4_1.remembertherecipe;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RememberTheRecipe {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] names = new String[count];
		int[] priority = new int[count];
		for (int i = 0; i < count; i++) {
			names[i] = in.readString();
			priority[i] = in.readInt();
		}
		long[][] hashes = new long[count][];
		Integer[] order = ListUtils.order(Array.wrap(priority));
		for (int i : order) {
			long hash = 0;
			hashes[i] = new long[names[i].length()];
			for (int j = 0; j < names[i].length(); j++) {
				hash *= 43;
				hash += names[i].charAt(j);
				hashes[i][j] = hash;
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			long hash = 0;
			String query = in.readString();
			for (int j = 0; j < query.length(); j++) {
				hash *= 43;
				hash += query.charAt(j);
			}
			String answer = null;
			for (int j : order) {
				if (query.length() <= names[j].length() && hashes[j][query.length() - 1] == hash)
					answer = names[j];
			}
			if (answer == null)
				answer = "NO";
			out.printLine(answer);
		}
	}
}
