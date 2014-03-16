package on2014_01.on2014_01_30_Single_Round_Match_606.EllysPairing;



import net.egork.collections.map.Counter;
import net.egork.misc.ArrayUtils;

import java.util.Map;

public class EllysPairing {
    public int getMax(int M, int[] count, int[] first, int[] mult, int[] add) {
		int size = count.length;
		int total = (int) ArrayUtils.sumArray(count);
		int answer = total >> 1;
		Counter<Integer> counter = new Counter<Integer>();
		int[] accountedFor = new int[size];
		for (int i = 0; i < size; i++) {
			int current = first[i];
			for (int j = 0; j < count[i] && j < 100; j++) {
				int next = (current * mult[i] + add[i]) & (M - 1);
				counter.add(current);
				current = next;
			}
			accountedFor[i] = Math.min(count[i], 100);
			int next = (current * mult[i] + add[i]) & (M - 1);
			if (accountedFor[i] + 1 == count[i] || current == next) {
				counter.add(current, count[i] - accountedFor[i]);
				accountedFor[i] = count[i];
				continue;
			}
			int nextNext = (next * mult[i] + add[i]) & (M - 1);
			if (nextNext == current) {
				int remaining = count[i] - accountedFor[i];
				counter.add(current, (remaining + 1) >> 1);
				counter.add(next, remaining >> 1);
				accountedFor[i] = count[i];
			}
		}
		if (counter.isEmpty())
			return answer;
		long all = 0;
		long max = 0;
		int maxAt = 0;
		for (Map.Entry<Integer, Long> entry : counter.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				maxAt = entry.getKey();
			}
			all += entry.getValue();
		}
		if (max * 2 <= all)
			return answer;
		for (int i = 0; i < size; i++) {
			if (count[i] == accountedFor[i])
				continue;
			int current = first[i];
			for (int j = 0; j < count[i]; j++) {
				if (current == maxAt && j >= accountedFor[i])
					max++;
				current = (current * mult[i] + add[i]) & (M - 1);
			}
		}
		return (int) Math.min(answer, total - max);
    }
}
