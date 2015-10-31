package on2015_09.on2015_09_05_Booking_com_Hackathon.Travel_Profiles;


import net.egork.collections.set.EHashSet;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Set;

public class TravelProfiles {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[] id = new long[n];
		long[] price = new long[n];
		Set<String>[] facilities = new Set[n];
		for (int i = 0; i < n; i++) {
			id[i] = in.readLong();
			price[i] = in.readLong();
			facilities[i] = new EHashSet<>(Arrays.asList(in.readLine().split(" ")));
		}
		int m = in.readInt();
		for (int i = 0; i < m; i++) {
			long maxPrice = in.readLong();
			String[] required = in.readLine().split(" ");
			IntList answer = new IntArrayList();
			for (int j = 0; j < n; j++) {
				if (price[j] > maxPrice) {
					continue;
				}
				boolean good = true;
				for (String s : required) {
					if (!facilities[j].contains(s)) {
						good = false;
						break;
					}
				}
				if (good) {
					answer.add(j);
				}
			}
			answer.sort((a, b) -> {
				if (facilities[a].size() != facilities[b].size()) {
					return facilities[b].size() - facilities[a].size();
				}
				if (price[a] != price[b]) {
					return Long.compare(price[a], price[b]);
				}
				return Long.compare(id[a], id[b]);
			});
			long[] ids = new long[answer.size()];
			int at = 0;
			for (int j : answer.toArray()) {
				ids[at++] = id[j];
			}
			out.printLine(ids);
		}
	}
}
