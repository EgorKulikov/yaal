package net.egork;

import net.egork.collections.map.CPPMap;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskE {
	String answer = null;
	private Map<Long,Integer> byHash;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] vocabulary = IOUtils.readStringArray(in, count);
		StringHash[] hashes = new StringHash[count];
		for (int i = 0; i < count; i++)
			hashes[i] = new SimpleStringHash(vocabulary[i]);
		@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
		Map<Long, List<Integer>> prefixes = new CPPMap<Long, List<Integer>>(new Factory<List<Integer>>() {
			public List<Integer> create() {
				return new ArrayList<Integer>();
			}
		});
		for (int i = 0; i < count; i++) {
			for (int j = 1; j < vocabulary[i].length(); j++)
				prefixes.get(hashes[i].hash(0, j)).add(i);
		}
		byHash = new HashMap<Long, Integer>();
		for (int i = 0; i < count; i++)
			byHash.put(hashes[i].hash(0), i);
		Set<Long> possible = new HashSet<Long>();
		possible.add(0L);
		for (int i = 0; i < count; i++) {
			for (int j = vocabulary[i].length() - 1; j >= 0; j--) {
				for (int k = vocabulary[i].length(); k > j; k--) {
					if (byHash.containsKey(hashes[i].hash(j, k))) {
						if (possible.contains(hashes[i].hash(k)))
							possible.add(hashes[i].hash(j));
						break;
					}
				}
			}
		}
		for (int i = 0; i < count; i++) {
			int[] queue = new int[vocabulary[i].length()];
			queue[0] = 0;
			boolean[] processed = new boolean[vocabulary[i].length()];
			processed[0] = true;
			int size = 1;
			for (int j = 0; j < size; j++) {
				int current = queue[j];
				for (int k = current + 1; k < vocabulary[i].length(); k++) {
					if (!processed[k] && byHash.containsKey(hashes[i].hash(current, k))) {
						processed[k] = true;
						queue[size++] = k;
					}
				}
			}
			Set<Long> bad = new HashSet<Long>();
			for (int j = 0; j < count; j++) {
				if (vocabulary[j].length() > vocabulary[i].length() && hashes[j].hash(0, vocabulary[i].length()) == hashes[i].hash(0))
					bad.add(hashes[j].hash(vocabulary[i].length()));
			}
			for (int j = 1; j < size; j++) {
				long key = hashes[i].hash(queue[j]);
				if (prefixes.containsKey(key)) {
					for (int k : prefixes.get(key)) {
						int newLength = queue[j] + vocabulary[k].length();
						if (answer != null && answer.length() <= newLength)
							continue;
						int startInK = vocabulary[i].length() - queue[j];
						if (possible.contains(hashes[k].hash(startInK)))
							continue;
						boolean good = true;
						for (int l = startInK + 1; l <= vocabulary[k].length(); l++) {
							if (bad.contains(hashes[k].hash(startInK, l))) {
								good = false;
								break;
							}
						}
						if (good)
							answer = vocabulary[i] + vocabulary[k].substring(startInK);
						
					}
				}
			}
		}
		if (answer == null)
			out.printLine("Good vocabulary!");
		else
			out.printLine(answer);
	}

	private boolean tryCandidate(StringHash hash) {
		int length = hash.length();
		if (answer != null && answer.length() <= length)
			return false;
		int start = 0;
		while (start != length) {
			boolean found = false;
			for (int i = length; i > start; i--) {
				if (byHash.containsKey(hash.hash(start, i))) {
					start = i;
					found = true;
					break;
				}
			}
			if (!found)
				return true;
		}
		return false;
	}
}
