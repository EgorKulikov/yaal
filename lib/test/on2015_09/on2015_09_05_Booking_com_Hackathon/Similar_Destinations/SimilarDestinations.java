package on2015_09.on2015_09_05_Booking_com_Hackathon.Similar_Destinations;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class SimilarDestinations {
	int required;
	String[] cities;
	String[] tags;
	int[][] masks;

	int[][] curMasks;
	int[] skip;

	List<Output> answer = new ArrayList<>();

	static class Output {
		int numTags;
		String description;

		public Output(int numTags, String description) {
			this.numTags = numTags;
			this.description = description;
		}
	}

	int[] taken;
	int numTaken;

	void go(int from, int depth) {
		for (int i = 0; i < cities.length; i++) {
			if (taken[i] <= depth || skip[i] <= depth) {
				continue;
			}
			boolean shouldTake = true;
			for (int j = 0; j < curMasks[depth].length; j++) {
				if ((curMasks[depth][j] & masks[i][j]) != curMasks[depth][j]) {
					shouldTake = false;
					break;
				}
			}
			if (shouldTake) {
				if (i < from) {
					return;
				}
				taken[i] = depth;
				numTaken++;
			}
		}

		if (numTaken >= 2) {
			StringBuilder current = new StringBuilder();
			for (int i = 0; i < cities.length; i++) {
				if (taken[i] <= depth) {
					if (current.length() != 0) {
						current.append(',');
					}
					current.append(cities[i]);
				}
			}
			current.append(':');
			int tagCount = 0;
			for (int i = 0; i < curMasks[depth].length; i++) {
				if (curMasks[depth][i] != 0) {
					for (int j = 0; j < 32; j++) {
						if ((curMasks[depth][i] >> j & 1) == 1) {
							if (tagCount != 0) {
								current.append(',');
							}
							tagCount++;
							current.append(tags[i * 32 + j]);
						}
					}
				}
			}
			answer.add(new Output(tagCount, current.toString()));
		}

		for (int i = from; i < cities.length; i++) {
			if (skip[i] <= depth || taken[i] <= depth) {
				continue;
			}

			int common = 0;
			for (int j = 0; j < curMasks[depth].length; j++) {
				common += Integer.bitCount(curMasks[depth][j] & masks[i][j]);
			}
			if (common < required) {
				skip[i] = depth;
			}
		}
		for (int i = from; i < cities.length; i++) {
			if (skip[i] > depth && taken[i] > depth) {
				taken[i] = depth;
				numTaken++;
				for (int j = 0; j < curMasks[depth].length; j++) {
					curMasks[depth + 1][j] = curMasks[depth][j] & masks[i][j];
				}
				go(i + 1, depth + 1);
				numTaken--;
				taken[i] = cities.length + 1;
			}
		}
		for (int i = 0; i < cities.length; i++) {
			if (skip[i] == depth) {
				skip[i] = cities.length + 1;
			}
			if (taken[i] == depth) {
				taken[i] = cities.length + 1;
				numTaken--;
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		required = in.readInt();
		List<String> descriptions = new ArrayList<>();
		while (!in.isExhausted()) {
			descriptions.add(in.readLine().trim());
		}
		Collections.sort(descriptions);
		cities = new String[descriptions.size()];
		String[][] tagByCity = new String[descriptions.size()][];
		for (int i = 0; i < cities.length; i++) {
			String[] cityAndTags = descriptions.get(i).split("[:]");
			cities[i] = cityAndTags[0];
			tagByCity[i] = cityAndTags[1].split("[,]");
		}
		NavigableSet<String> allTags = new TreeSet<>();
		for (String[] row : tagByCity) {
			allTags.addAll(Arrays.asList(row));
		}
		tags = allTags.toArray(new String[allTags.size()]);
		masks = new int[cities.length][(tags.length + 31) >> 5];
		skip = ArrayUtils.createArray(cities.length, cities.length + 1);
		taken = skip.clone();
		curMasks = new int[cities.length + 1][(tags.length + 31) >> 5];
		for (int i = 0; i < curMasks[0].length; i++) {
			curMasks[0][i] = -1;
		}
		for (int i = 0; i < cities.length; i++) {
			for (String tag : tagByCity[i]) {
				int id = Arrays.binarySearch(tags, tag);
				masks[i][id >> 5] |= 1 << (id & 31);
			}
		}
		go(0, 0);
		Collections.sort(answer, (a, b) -> a.numTags != b.numTags ? b.numTags - a.numTags : a.description.compareTo(b.description));
		for (Output output : answer) {
			out.printLine(output.description);
		}
	}
}
