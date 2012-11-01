package on2012_10.on2012_10_14_OpenCup_SPb_GP.CoveringGame;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.StringWrapper;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CoveringGame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int countCharacters = in.readInt();
		String s = "";
		if (countCharacters != 0)
			s = in.readString();
		int count = CollectionUtils.count(StringWrapper.wrap(s), 'l') + CollectionUtils.count(StringWrapper.wrap(s), 'r') + 1;
		for (int i = 0; i < 3; i++) {
			if (count % 3 == i)
				out.printLine("Yes");
			else
				out.printLine("No");
		}
	}
}
