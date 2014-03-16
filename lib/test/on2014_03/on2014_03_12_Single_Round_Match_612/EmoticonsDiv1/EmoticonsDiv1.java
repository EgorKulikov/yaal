package on2014_03.on2014_03_12_Single_Round_Match_612.EmoticonsDiv1;



import net.egork.misc.ArrayUtils;

public class EmoticonsDiv1 {
    public int printSmiles(int smiles) {
		int[][] answer = new int[2 * smiles][2 * smiles];
		ArrayUtils.fill(answer, -1);
		answer[1][0] = 0;
		int[] queue = new int[4 * smiles * smiles];
		queue[0] = 1 << 15;
		int mask = (1 << 15) - 1;
		int size = 1;
		for (int i = 0; i < size; i++) {
			int current = queue[i] >> 15;
			int clipboard = queue[i] & mask;
			if (current == smiles)
				return answer[current][clipboard];
			if (current > 0 && answer[current - 1][clipboard] == -1) {
				answer[current - 1][clipboard] = answer[current][clipboard] + 1;
				queue[size++] = ((current - 1) << 15) + clipboard;
			}
			if (answer[current][current] == -1) {
				answer[current][current] = answer[current][clipboard] + 1;
				queue[size++] = (current << 15) + current;
			}
			if (current + clipboard < 2 * smiles && answer[current + clipboard][clipboard] == -1) {
				answer[current + clipboard][clipboard] = answer[current][clipboard] + 1;
				queue[size++] = ((current + clipboard) << 15) + clipboard;
			}
		}
		throw new IllegalStateException();
    }
}
