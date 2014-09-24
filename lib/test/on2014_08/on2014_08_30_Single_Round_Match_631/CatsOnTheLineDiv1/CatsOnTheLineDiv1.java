package on2014_08.on2014_08_30_Single_Round_Match_631.CatsOnTheLineDiv1;



import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class CatsOnTheLineDiv1 {
    public int getNumber(int[] position, int[] count, int time) {
		int size = position.length;
		int[] special = new int[2 * size];
		for (int i = 0; i < size; i++) {
			special[2 * i] = position[i] - time;
			special[2 * i + 1] = position[i] + time + 1;
		}
		Arrays.sort(special);
		int answer = 0;
		ArrayUtils.orderBy(position, count);
		for (int i = 0; i < 2 * size - 1; i++) {
			int remaining = special[i + 1] - special[i];
			for (int j = 0; j < size; j++) {
				if (special[i] - position[j] > time && count[j] != 0) {
					answer++;
					for (int k = 0; k < size; k++) {
						if (Math.abs(position[k] - (special[i] - 1)) <= time) {
							count[k] = 0;
						}
					}
					break;
				}
			}
			while (remaining != 0) {
				int current = -1;
				for (int j = 0; j < size; j++) {
					if (count[j] != 0 && position[j] - special[i] <= time) {
						current = j;
						break;
					}
				}
				if (current == -1) {
					break;
				}
				int delta = Math.min(remaining, count[current]);
				count[current] -= delta;
				remaining -= delta;
			}
		}
		if (ArrayUtils.count(count, 0) != size) {
			answer++;
		}
		return answer;
    }
}
