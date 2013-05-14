package on2013_04.on2013_04_20_2013_TopCoder_Open_Algorithm.ScotlandYard;



import net.egork.misc.ArrayUtils;

public class ScotlandYard {
	int[][] answer;
	boolean[][] onPath;
	long[] taxiMask, busMask, metroMask;

    public int maxMoves(String[] taxi, String[] bus, String[] metro) {
		int count = taxi.length;
		taxiMask = convert(taxi);
		busMask = convert(bus);
		metroMask = convert(metro);
		answer = new int[count][count];
		onPath = new boolean[count][count];
		ArrayUtils.fill(answer, -1);
		int result = 0;
		try {
			for (int i = 0; i < count; i++) {
				for (int j = i + 1; j < count; j++)
					result = Math.max(result, go(i, j));
			}
		} catch (Success success) {
			return -1;
		}
		return result;
    }

	private int go(int first, int second) throws Success {
		if (onPath[first][second])
			throw new Success();
		if (answer[first][second] != -1)
			return answer[first][second];
		onPath[first][second] = true;
		answer[first][second] = 0;
		answer[first][second] = Math.max(answer[first][second], process(taxiMask[first] | taxiMask[second]));
		answer[first][second] = Math.max(answer[first][second], process(busMask[first] | busMask[second]));
		answer[first][second] = Math.max(answer[first][second], process(metroMask[first] | metroMask[second]));
		onPath[first][second] = false;
		return answer[first][second];
	}

	private int process(long mask) throws Success {
		if (mask == 0)
			return 0;
		int result = 1;
		for (int i = 0; i < taxiMask.length; i++) {
			if ((mask >> i & 1) == 0)
				continue;
			for (int j = i + 1; j < taxiMask.length; j++) {
				if ((mask >> j & 1) != 0)
					result = Math.max(result, go(i, j) + 1);
			}
		}
		return result;
	}

	static class Success extends Exception {
	}

	private long[] convert(String[] graph) {
		long[] result = new long[graph.length];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i].charAt(j) == 'Y')
					result[i] += 1L << j;
			}
		}
		return result;
	}
}
