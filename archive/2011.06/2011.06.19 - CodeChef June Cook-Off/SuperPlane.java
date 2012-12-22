import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class SuperPlane implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int flightCount = in.readInt();
		int[] departureCity = new int[flightCount];
		final int[] departureTime = new int[flightCount];
		int[] arrivalCity = new int[flightCount];
		int[] arrivalTime = new int[flightCount];
		IOUtils.readIntArrays(in, departureCity, departureTime, arrivalCity, arrivalTime);
		int city = in.readInt();
		int time = in.readInt();
		int targetCity = in.readInt();
		int targetTime = in.readInt();
		int[] outboundFlightCount = new int[10001];
		for (int from : departureCity)
			outboundFlightCount[from]++;
		Pair<Integer, Integer>[][] flight = new Pair[10001][];
		for (int i = 0; i <= 10000; i++)
			flight[i] = new Pair[outboundFlightCount[i]];
		for (int i = 0; i < flightCount; i++)
			flight[departureCity[i]][--outboundFlightCount[departureCity[i]]] = Pair.makePair(departureTime[i], i);
		for (int i = 0; i <= 10000; i++)
			Arrays.sort(flight[i]);
		boolean[] used = new boolean[flightCount];
		int answer = 0;
		while (city != targetCity || time > targetTime) {
			int index = -Arrays.binarySearch(flight[city], Pair.makePair(time, -1)) - 1;
			if (index == flight[city].length || used[flight[city][index].second]) {
				out.println("No");
				return;
			}
			answer++;
			index = flight[city][index].second;
			used[index] = true;
			city = arrivalCity[index];
			time = arrivalTime[index];
		}
		out.println("Yes " + answer);
	}
}

