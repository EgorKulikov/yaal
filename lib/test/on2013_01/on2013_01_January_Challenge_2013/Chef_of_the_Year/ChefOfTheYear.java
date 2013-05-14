package on2013_01.on2013_01_January_Challenge_2013.Chef_of_the_Year;



import net.egork.collections.CollectionUtils;
import net.egork.collections.map.Counter;
import net.egork.collections.map.EHashMap;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.Map;

public class ChefOfTheYear {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		Map<String, String> map = new EHashMap<String, String>();
		for (int i = 0; i < count; i++) {
			String chef = in.readString();
			String country = in.readString();
			map.put(chef, country);
		}
		Counter<String> chefs = new Counter<String>();
		Counter<String> countries = new Counter<String>();
		for (int i = 0; i < queryCount; i++) {
			String chef = in.readString();
			chefs.add(chef);
			countries.add(map.get(chef));
		}
		out.printLine(winner(countries));
		out.printLine(winner(chefs));
    }

	private String winner(final Counter<String> counter) {
		return CollectionUtils.maxElement(counter.keySet(), new Comparator<String>() {
			public int compare(String o1, String o2) {
				int result = IntegerUtils.longCompare(counter.get(o1), counter.get(o2));
				if (result != 0)
					return result;
				return o2.compareTo(o1);
			}
		});
	}
}
