package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] incoming = IOUtils.readIntArray(in, count);
		int friendCount = in.readInt();
		int[] from = new int[friendCount];
		int[] to = new int[friendCount];
		final int[] food = new int[friendCount];
		IOUtils.readIntArrays(in, from, to, food);
		MiscUtils.decreaseByOne(from, to);
		int[] friendsPerDay = new int[count];
		for (int i = 0; i < friendCount; i++) {
			for (int j = from[i]; j <= to[i]; j++)
				friendsPerDay[j]++;
		}
		int[][] day = new int[count][];
		int[][] friendsByIndex = new int[count][];
		for (int i = 0; i < count; i++) {
			day[i] = new int[friendsPerDay[i]];
			friendsByIndex[i] = new int[friendsPerDay[i]];
		}
		for (int i = 0; i < friendCount; i++) {
			for (int j = from[i]; j <= to[i]; j++) {
				day[j][--friendsPerDay[j]] = food[i];
				friendsByIndex[j][friendsPerDay[j]] = i;
			}
		}
		for (int[] row : day)
			Arrays.sort(row);
		for (int i = 0; i < count; i++) {
			for (int j = 1; j < day[i].length; j++)
				day[i][j] += day[i][j - 1];
		}
		int[] rating = new int[401];
		Arrays.fill(rating, Integer.MIN_VALUE);
		rating[0] = 0;
		int[] next = new int[401];
		int[][] lastFeed = new int[count][401];
		int[][] lastIndex = new int[count][401];
		for (int i = 0; i < count; i++) {
			Arrays.fill(next, Integer.MIN_VALUE);
			for (int j = 0; j <= 400; j++) {
				if (rating[j] == Integer.MIN_VALUE)
					continue;
				int curFood = j + incoming[i] - required;
				int curRating = rating[j];
				if (curFood >= 0) {
					int l = Math.min(curFood, incoming[i]);
					if (next[l] < curRating) {
						next[l] = curRating;
						lastFeed[i][l] = 0;
						lastIndex[i][l] = j;
					}
				}
				for (int k = 0; k < day[i].length; k++) {
					curRating++;
					if (curFood >= day[i][k]) {
						int l = Math.min(curFood - day[i][k], incoming[i]);
						if (next[l] < curRating) {
							next[l] = curRating;
							lastFeed[i][l] = k + 1;
							lastIndex[i][l] = j;
						}
					} else
						break;
				}
			}
			int[] temp = rating;
			rating = next;
			next = temp;
		}
		int current = ArrayUtils.maxPosition(rating);
		out.printLine(rating[current]);
		int[][] answer = new int[count][];
		for (int i = count - 1; i >= 0; i--) {
			ArrayUtils.sort(friendsByIndex[i], new IntComparator() {
				public int compare(int first, int second) {
					return food[first] - food[second];
				}
			});
			answer[i] = Arrays.copyOf(friendsByIndex[i], lastFeed[i][current]);
			for (int j = 0; j < answer[i].length; j++)
				answer[i][j]++;
			current = lastIndex[i][current];
		}
		for (int[] row : answer) {
			out.print(row.length + " ");
			out.printLine(row);
		}
	}
}
