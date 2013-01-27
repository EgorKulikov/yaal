package on2012_05.on2012_4_1.jewelsandstones;



import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.StringWrapper;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String jewels = in.readString();
		String stones = in.readString();
		final Set<Character> setJewels = new HashSet<Character>(StringWrapper.wrap(jewels));
		int answer = CollectionUtils.count(StringWrapper.wrap(stones), new Filter<Character>() {
			public boolean accept(Character value) {
				return setJewels.contains(value);
			}
		});
		out.printLine(answer);
	}
}
