package on2015_12.on2015_12_03_NEERC_2015_Practice_Session.Coin;



import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Coin {
	IntSet[] left;
	IntSet[] right;
	char[] result;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		left = new IntSet[k];
		right = new IntSet[k];
		result = new char[k];
		for (int i = 0; i < k; i++) {
			int p = in.readInt();
			int[] onLeft = IOUtils.readIntArray(in, p);
			int[] onRight = IOUtils.readIntArray(in, p);
			result[i] = in.readCharacter();
			MiscUtils.decreaseByOne(onLeft, onRight);
			left[i] = new IntHashSet(onLeft);
			right[i] = new IntHashSet(onRight);
		}
		IntSet valid = new IntHashSet();
		for (int i = 0; i < n; i++) {
			if (ok(i, false) || ok(i, true))
				valid.add(i);
		}
		if (valid.size() == 1)
			out.printLine(valid.first() + 1);
		else
			out.printLine(0);
	}

	private boolean ok(int at, boolean more) {
		for (int i = 0; i < result.length; i++) {
			if (result[i] == '=') {
				if (left[i].contains(at) || right[i].contains(at))
					return false;
			} else if ((result[i] == '>') ^ more) {
				if (!right[i].contains(at))
					return false;
			} else if (!left[i].contains(at))
				return false;
		}
		return true;
	}
}
