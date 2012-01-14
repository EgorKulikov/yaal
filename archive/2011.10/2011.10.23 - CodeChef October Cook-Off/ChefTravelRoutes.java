package net.egork;

import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class ChefTravelRoutes {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int cityCount = in.readInt();
		String[] cities = IOUtils.readStringArray(in, cityCount);
		Set<String> set = new HashSet<String>(Arrays.asList(cities));
		int edgeCount = in.readInt();
		Map<Pair<String, String>, Integer> edges = new HashMap<Pair<String, String>, Integer>();
		for (int i = 0; i < edgeCount; i++) {
			String from = in.readString();
			String to = in.readString();
			edges.put(Pair.makePair(from, to), in.readInt());
		}
		int routeCount = in.readInt();
		for (int i = 0; i < routeCount; i++) {
			int length = in.readInt();
			String[] route = IOUtils.readStringArray(in, length);
			boolean valid = true;
			Set<String> was = new HashSet<String>();
			for (String city : route) {
				if (!set.contains(city) || was.contains(city)) {
					valid = false;
					break;
				}
				was.add(city);
			}
			int result = 0;
			if (valid) {
				for (int j = 1; j < length && valid; j++) {
					Integer segment = edges.get(Pair.makePair(route[j - 1], route[j]));
					if (segment == null)
						valid = false;
					else
						result += segment;
				}
			}
			if (valid)
				out.println(result);
			else
				out.println("ERROR");
		}
	}
}
