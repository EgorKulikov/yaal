package April2011.UVaHugeEasyContestII;

import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskM implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int activityCount = in.readInt();
		int countryCount = in.readInt();
		int money = in.readInt() * 1000000;
		char[] name = new char[activityCount];
		int[] fixed = new int[activityCount];
		int[] variable = new int[activityCount];
		for (int i = 0; i < activityCount; i++) {
			name[i] = in.readCharacter();
			fixed[i] = in.readInt() * 1000;
			variable[i] = in.readInt();
		}
		int[][] cost = new int[countryCount][activityCount];
		int[][] population = new int[countryCount][activityCount];
		boolean[][] isApplicable = new boolean[countryCount][activityCount];
		for (int i = 0; i < countryCount; i++) {
			int applicable = in.readInt();
			int currentPopulation = in.readInt();
			for (int j = 0; j < applicable; j++) {
				char type = in.readCharacter();
				int percent = Integer.parseInt(in.readString().replace('%', ' ').trim());
				if (currentPopulation == 0)
					continue;
				int actualPopulation = (currentPopulation * percent + 50) / 100;
				for (int k = 0; k < activityCount; k++) {
					if (name[k] == type) {
						if (money - fixed[k] / currentPopulation >= variable[k]) {
							isApplicable[i][k] = true;
							population[i][k] = actualPopulation;
							cost[i][k] = fixed[k] + currentPopulation * variable[k];
						}
					}
				}
			}
		}
		out.println(go(isApplicable, cost, population, new boolean[activityCount], 0, money, 0));
	}

	private long go(boolean[][] applicable, int[][] cost, int[][] population, boolean[] used, long result, int money, int country) {
		if (money < 0)
			return 0;
		if (country == applicable.length)
			return result;
		long answer = go(applicable, cost, population, used, result, money, country + 1);
		for (int i = 0; i < applicable[country].length; i++) {
			if (!used[i] && applicable[country][i]) {
				used[i] = true;
				answer = Math.max(answer, go(applicable, cost, population, used, result + population[country][i], money - cost[country][i], country + 1));
				used[i] = false;
			}
		}
		return answer;
	}
}

